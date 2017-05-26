package org.fontys.course.registration.model;

import java.math.BigInteger;
import java.security.SecureRandom;

public final class SessionIdentifierGenerator {
	    private static final SecureRandom random = new SecureRandom();

	    public SessionIdentifierGenerator() {
	    }

	    public static String NextReplyQueue() {
	        return new BigInteger(130, random).toString(32);
	    }
}