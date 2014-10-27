package org.kay.framework.persistence.util;

import static org.junit.Assert.*;
import java.sql.Timestamp;
import org.junit.Test;

public class NativeDBUtilTest {

	@Test
	public void testGenSequenceNo() {
		//NativeDBUtil.getInstance().genSequenceNo(sequenceName);
	}

	@Test
	public void testGetNativeTimestamp() {
		Timestamp ts = NativeDBUtil.getInstance().getNativeTimestamp();
		assertTrue(ts != null);
	}

}
