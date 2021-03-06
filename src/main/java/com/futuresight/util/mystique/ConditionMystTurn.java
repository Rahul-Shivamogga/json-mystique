/*
 * Copyright (c) Balajee TM 2016.
 * All rights reserved.
 * License -  @see <a href="http://www.apache.org/licenses/LICENSE-2.0"></a>
 */

/*
 * Created on 25 Aug, 2016 by balajeetm
 */
package com.futuresight.util.mystique;

import java.util.List;

import org.springframework.stereotype.Component;

import com.futuresight.util.mystique.lever.MysCon;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * The Class ConditionMystTurn.
 *
 * @author balajmoh
 */
@Component
public class ConditionMystTurn extends AbstractMystTurn {

	/* (non-Javadoc)
	 * @see com.futuresight.util.mystique.AbstractMystique#transmute(java.util.List, com.google.gson.JsonObject, com.google.gson.JsonObject)
	 */
	@Override
	protected JsonElement transmute(List<JsonElement> source, JsonObject deps, JsonObject aces, JsonObject turn) {
		JsonElement transform = null;
		JsonElement elementSource = jsonLever.getFirst(source);
		turn = jsonLever.getAsJsonObject(turn, new JsonObject());
		JsonElement granularSource = getGranularSource(elementSource, turn, deps, aces);
		JsonElement value = turn.get(MysCon.VALUE);
		Boolean equals = isEquals(granularSource, value);
		JsonObject defaultJson = jsonLever.getAsJsonObject(turn.get(String.valueOf(equals)));
		transform = jsonLever.isNull(defaultJson) ? new JsonPrimitive(equals) : transformToDefault(defaultJson,
				source, deps, aces);
		return transform;
	}

	/**
	 * Checks if is equals.
	 *
	 * @param source the source
	 * @param expected the expected
	 * @return the boolean
	 */
	private Boolean isEquals(JsonElement source, JsonElement expected) {
		Boolean isEqual = Boolean.FALSE;
		// This is to check the existence case. The condition is to check the existence of the element
		if (null == expected) {
			if (jsonLever.isNotNull(source)) {
				isEqual = Boolean.TRUE;
			}
		}
		else {
			if (jsonLever.isNull(source) && expected.isJsonNull()) {
				isEqual = Boolean.TRUE;
			}
			isEqual = expected.equals(source);
		}
		return isEqual;
	}
}
