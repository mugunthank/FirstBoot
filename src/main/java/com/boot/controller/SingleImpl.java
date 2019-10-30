package com.boot.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public class SingleImpl {
	public static void main(String[] args) {

		Single<String> single = Observable.just("Hello").singleOrError().doOnSuccess(System.out::print).doOnError(e -> {
			throw new RuntimeException(e.getMessage());
		});
		single.subscribe();

		List<String> letters = Arrays.asList("A", "B", "C", "D", "E");
		List<String> results = new ArrayList<>();
		Observable<String> observable = Observable.fromArray(letters).zipWith(Observable.range(1, Integer.MAX_VALUE),
				(string, index) -> index + "-" + string);

		observable.subscribe(results::add);
	}
}
