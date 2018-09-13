package com.example;

import org.junit.Assert;
import org.junit.Test;

public class BowlingTest {

	/**
	 * The pins knocked down in open frames are summed.
	 */
	@Test
	public void testTwoOpenFrames() {
		Assert.assertEquals(1 + 2 + 3 + 4, new Bowling().calculateScore("1,2,3,4"));
	}
	
	@Test
	public void testMiss() {
		Assert.assertEquals(1 + 0, new Bowling().calculateScore("1,0"));
	}
	
	/**
	 * A spare gives a score of 10 plus the score of the next roll.
	 */
	@Test
	public void testSpareFollowedByOpenFrame() {
		Assert.assertEquals(0 + (10 + 3) + 3 + 1, new Bowling().calculateScore("1,/,3,1"));
	}
	
	/**
	 * A strike gives a score of 10 plus the score of the next two rolls.
	 */
	@Test
	public void testStrikeFollowedByOpenFrame() {
		Assert.assertEquals(1 + 1 + (10 + 3 + 4) + 3 + 4 + 1 + 1, new Bowling().calculateScore("1,1,X,3,4,1,1"));
	}
	
	@Test
	public void testFinishingOnSpare() {
		Assert.assertEquals(0 + 10, new Bowling().calculateScore("1,/"));
	}
	
	@Test
	public void testFinishingOnStrike() {
		Assert.assertEquals(1 + 2 + 10, new Bowling().calculateScore("1,2,X"));
	}
	
	@Test
	public void testStrikeFollowedBySpareFrame() {
		Assert.assertEquals(1 + 1 + (10 + 10) + 0 + (10 + 1) + 1 + 1, new Bowling().calculateScore("1,1,X,3,/,1,1"));
	}
	
	@Test
	public void testDoubleStrike() {
		Assert.assertEquals((10 + 10 + 1) + (10 + 1 + 1) + 1 + 1 + 1 + 1, new Bowling().calculateScore("X,X,1,1,1,1"));
	}
	
	@Test
	public void testTripleStrike() {
		Assert.assertEquals((10 + 10 + 10) + (10 + 10 + 1) + (10 + 1 + 1) + 1 + 1 + 1 + 1, new Bowling().calculateScore("X,X,X,1,1,1,1"));
	}
	
	@Test
	public void testPerfectGame() {
		Assert.assertEquals(300, new Bowling().calculateScore("X,X,X,X,X,X,X,X,X,X,X,X"));
	}
}
