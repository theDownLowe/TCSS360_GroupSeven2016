package tests;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.Paper;
import model.Reviewer;
import model.SubProgramChair;
import model.User;
/**
 * test for subprogram chair.
 * @author shao-han wang (kevin)
 * @version 5/22/2016
 */
public class SubProgramChairTest {
	
	
	ArrayList<User> myUserList;
	ArrayList<Reviewer> myRev;
	SubProgramChair test1;
	SubProgramChair test2;
	Paper thePaper;
	Paper thePaper2;
	User user;
	Reviewer someR;
	
	@Before
	public void setUp() {
		myUserList = new ArrayList<User>();
		myRev = new ArrayList<Reviewer>();
		user = new User("wi", "fi", "wifi@everywhere.com");
		someR = new Reviewer("so", "me", "some@one.com");
		thePaper = new Paper("wifi@everywhere.com");
		thePaper2 = new Paper("JRedd@gmail.com");
		
		// Creating Users List.
		User testUser = new User("James", "Redding", "JRedd@gmail.com");
		User testUser2 = new User("Sam", "Hag", "SamH@gmail.com");
		User testUser3 = new User("Aaron", "Scott", "AScott@gmail.com");
		myUserList.add(testUser);
		myUserList.add(testUser2);
		myUserList.add(testUser3);
		
		test1 = new SubProgramChair("Jeremy", "Wolf", "Jwolf059@uw.edu", myUserList, myRev);
		
		ArrayList<Reviewer> rev2 = new ArrayList<Reviewer>();
		rev2.add(someR);
		ArrayList<User> UserList = new ArrayList<User>();
		UserList.add(user);
		test2 = new SubProgramChair("Jeremy", "Wolf", "Jwolf059@uw.edu", UserList, rev2);
	}
	
	/**
	 * Recommended.
	 */
	@Test
	public void testMakeRecommendation_Recommended(){
		test1.makeRecommendation(1, thePaper);
		assertTrue("makeRecommendation Recommended failed", thePaper.getRecommendation());
	}
	/**
	 * not recommended.
	 */
	@Test
	public void testMakeRecommendation_notRecommended(){
		test1.makeRecommendation(2, thePaper);
		assertFalse("makeRecommendation choice 2 failed", thePaper.getRecommendation());
	}
	
	/**
	 * the chosen user is not the author of the paper.
	 */
	@Test
	public void testIsAuthor_notAuthor(){
		assertEquals("Is the Author, should not be the author", false,  test1.isAuthor(2, thePaper2));

	}
	/**
	 * the chosen user is the author of the paper.
	 */
	@Test
	public void testIsAuthor_isAuthor(){
		assertEquals("Is not the Author, Should be the Author", true ,test2.isAuthor(1, thePaper));
	}
	
	
	/**
	 * empty reviewer list.
	 */
	@Test
	public void testCreateReviewer_empty(){
		assertTrue ("Review List is not empty", test1.getReviewers().isEmpty());
		Reviewer testRev = test1.createReviewer(1);
		assertEquals("Empty CreateReview Failed", 1, test1.getReviewers().size());
		String compareID = test1.getReviewers().get(0).getID();
		assertEquals("ID of the Reviewer did not match one in the list", "JRedd@gmail.com", compareID);
		assertEquals("ID of the Reviewer did not match returned value", "JRedd@gmail.com", testRev.getID());
	}
	/**
	 * the user is a reviewer. no paper.
	 */
	@Test
	public void testCreateReviewer_isReviewer(){
		assertTrue ("Review List is not empty", test1.getReviewers().isEmpty());
		Reviewer testRev = test1.createReviewer(1); // Add to empty list
		assertEquals("List does not contain 1 Reviewer", 1, test1.getReviewers().size());
		Reviewer testRev2 = test1.createReviewer(1); // Add again, since it is the same it will not be added.
		assertEquals("Duplicated Reviewer was Added", 1, test1.getReviewers().size());
		assertEquals("ID of the Reviewer did not match", testRev.getID(), testRev2.getID());
	}
	/**
	 * the subProgram Chair has max papers.
	 */
	@Test
	public void testaddPaper_exceedMaxPaper(){
		Paper p1 = new Paper("Test 1");
		Paper p2 = new Paper("Test 2");
		Paper p3 = new Paper("Test 3");
		Paper p4 = new Paper("Test 4");
		Paper p5 = new Paper("Test 5");
		
		test1.addPaper(p1);
		test1.addPaper(p2);
		test1.addPaper(p3);
		test1.addPaper(p4);
		
		assertFalse("Paper was added, Max paper was not enforced", test1.addPaper(p5));
	}
	/**
	 * new reviewer, user list is not empty.
	 */
	@Test
	public void testCreateReviewer_notEmptyList(){
		assertTrue ("Review List is not empty", test1.getReviewers().isEmpty());
		Reviewer testRev = test1.createReviewer(1);
		assertEquals("Empty CreateReview Failed", 1, test1.getReviewers().size());
		Reviewer testRev2 = test1.createReviewer(2);
		assertEquals("Empty CreateReview Failed, should be 2", 2, test1.getReviewers().size());
		assertEquals("ID of the Reviewer did not match returned value", "SamH@gmail.com", testRev2.getID());
	}
	
	
	@Test
	public void testAddPaper() {
		test1.addPaper(thePaper2);
		ArrayList<Paper> temp = test1.getPaperList();
		
		
		if(thePaper.getTitle().equals(temp.get(0).getTitle())) {
				assertTrue("Paper was added", true);
		} else {
			assertFalse("Paper was not added", false);
		}		
	}

	@Test
	public void testGetFirst() {
		if(test1.getFirst().equals("Jeremy")) {
			assertTrue("First names match", true);
		} else {
			assertFalse("First name did not match", false);
		}
	}

	@Test
	public void testGetLast() {
		if(test1.getLast().equals("Wolf")) {
			assertTrue("Last names match", true);
		} else {
			assertFalse("Last name did not match", false);
		}
	}
	@Test
	public void testGetID() {
		assertEquals("getID failed", "Jwolf059@uw.edu", test1.getID());
	}
	@Test
	public void testGetList() {
		assertEquals("getList (user list) failed", myUserList, test1.getList());
	}
}
