/*
 * Copyright (c) Balajee TM 2016.
 * All rights reserved.
 * License -  @see <a href="http://www.apache.org/licenses/LICENSE-2.0"></a>
 */

/*
 * Created on 25 Aug, 2016 by balajeetm
 */
package com.futuresight.util.mystique.lever;

/**
 * The Class ConvertorException.
 *
 * @author balajeetm
 */
public class ConvertorException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new convertor exception.
	 * 
	 * @param cause the cause
	 */
	public ConvertorException(Throwable cause) {
		super(String.format("Error during conversion : %s", cause.getMessage()));
		this.initCause(cause);
	}

	/**
	 * Instantiates a new convertor exception.
	 * 
	 * @param message the message
	 */
	public ConvertorException(String message) {
		super(String.format("Error during conversion : %s", message));
	}
}
