package com.boot.controller;

import java.util.Timer;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.boot.model.BaseResponse;
import com.boot.model.MobileAcknowledgementRequest;
import com.boot.model.ProcessingStatus;
import com.boot.model.ProcessingTask;
import com.boot.model.Response;
import com.boot.model.StudentRegistration;

@RestController
public class NonBlockingController {

	private static final Logger LOG = LoggerFactory.getLogger(NonBlockingController.class);

	private static final AtomicLong lastRequestId = new AtomicLong(0);
	private static final AtomicLong concurrentRequests = new AtomicLong(0);
	private static long maxConcurrentRequests = 0;

	private Timer timer = new Timer();

	@RequestMapping(method = RequestMethod.POST, value = "/nonblocking")
	@ResponseBody
	public DeferredResult<ProcessingStatus> nonBlockingProcessing(
			@RequestBody final StudentRegistration studentRegistration) {

		long reqId = lastRequestId.getAndIncrement();
		long concReqs = concurrentRequests.getAndIncrement();

		// updateStatistics(reqId, concReqs);

		// int processingTimeMs = calculateProcessingTime(minMs, maxMs);
		int processingTimeMs = 5000;
		LOG.info("{}: Start non-blocking request #{}, processing time: {} ms.", concReqs, reqId, processingTimeMs);

		// Create the deferredResult and initiate a callback object, task, with it
		DeferredResult<ProcessingStatus> deferredResult = new DeferredResult<>();
		ProcessingTask task = new ProcessingTask(reqId, concurrentRequests, processingTimeMs, deferredResult);

		// Schedule the task for asynch completion in the future
		timer.schedule(task, processingTimeMs);

		LOG.info("{}: Processing of non-blocking request #{} leave the request thread", concReqs, reqId);

		// Return to let go of the precious thread we are holding on to...
		return deferredResult;
	}

	@RequestMapping(value = "/acknowledgedelivery", method = RequestMethod.POST, headers = "Accept=application/json")
	public DeferredResult<ResponseEntity<Response<BaseResponse>>> acknowledgeDelivery(
			@RequestBody MobileAcknowledgementRequest mobileAcknowledgementRequest) throws Exception {
		LOG.info("Inside Controller");
		DeferredResult<ResponseEntity<Response<BaseResponse>>> output = new DeferredResult<>();

		ForkJoinPool.commonPool().submit(() -> {
			try {
				LOG.info("Before sleep");
				Thread.sleep(1000);
				LOG.info("After sleep");
			} catch (InterruptedException e) {
			}
			Response<BaseResponse> response = new Response<>();
			LOG.info("Going to set result");
			output.setResult(new ResponseEntity<Response<BaseResponse>>(response, HttpStatus.OK));
		});
		LOG.info("Releasing Server Thread");
		return output;
	}

}
