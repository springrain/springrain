package org.springrain.frame.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FrameObjectMapper extends ObjectMapper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FrameObjectMapper(){
		   this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		   this.getFactory().setCharacterEscapes(new HTMLCharacterEscapes());
	}
}
