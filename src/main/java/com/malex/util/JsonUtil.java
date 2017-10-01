package com.malex.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

	public static Gson buildGson() {
		return new GsonBuilder().addSerializationExclusionStrategy(getExclusionStrategy())
				  .create();
	}

	private static ExclusionStrategy getExclusionStrategy() {
		return new ExclusionStrategy() {

			public boolean shouldSkipField(FieldAttributes fas) {
				return (null != fas.getAnnotation(InvisibleJson.class));
			}

			public boolean shouldSkipClass(Class<?> classO) {
				return (null != classO.getAnnotation(InvisibleJson.class));
			}

		};
	}

}