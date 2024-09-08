package com.example.demo;

import com.example.demo.controllers.*;
import com.example.demo.model.persistence.*;
import com.example.demo.model.persistence.repositories.*;
import com.example.demo.model.requests.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SareetaApplicationTests {
	private CartController cartController;
	private ItemController itemController;
	private OrderController orderController;
	private UserController userController;
	private final CartRepository cartRepository = mock(CartRepository.class);
	private final ItemRepository itemRepository = mock(ItemRepository.class);
	private final OrderRepository orderRepository = mock(OrderRepository.class);
	private final UserRepository userRepository = mock(UserRepository.class);
	private final BCryptPasswordEncoder encoder = mock(BCryptPasswordEncoder.class);

	public static final String USER_1 = "User1";
	public static final String USER_2 = "User2";
	public static final String DEFAULT_PASSWORD = "password";

	/**
	 * Setup
	 */
//	@Before
//	public void setup() {
//		cartController = new CartController();
//		itemController = new ItemController();
//		orderController = new OrderController();
//		userController = new UserController();
//
//		Utils.injectObjects(cartController, "userRepository", userRepository);
//		Utils.injectObjects(cartController, "itemRepository", itemRepository);
//		Utils.injectObjects(cartController, "cartRepository", cartRepository);
//		Utils.injectObjects(itemController, "itemRepository", itemRepository);
//		Utils.injectObjects(orderController, "userRepository", userRepository);
//		Utils.injectObjects(orderController, "orderRepository", orderRepository);
//		Utils.injectObjects(userController, "userRepository", userRepository);
//		Utils.injectObjects(userController, "cartRepository", cartRepository);
//		Utils.injectObjects(userController, "bCryptPasswordEncoder", encoder);
//	}
//
//	/**
//	 * Test Add To Cart When Valid Request Then Return Successfully
//	 */
//	@Test
//	public void testAddToCartWhenValidRequestThenReturnSuccessfully() {
//		when(userRepository.findByUsername(USER_1)).thenReturn(Utils.createUser());
//		when(itemRepository.findById(1L)).thenReturn(Optional.of(Utils.createNewItem1()));
//		ModifyCartRequest request = new ModifyCartRequest();
//		request.setUsername(USER_1);
//		request.setItemId(1L);
//		request.setQuantity(1);
//
//		ResponseEntity<Cart> response = cartController.addTocart(request);
//		Assert.assertNotNull(response);
//		Assert.assertEquals(200, response.getStatusCodeValue());
//		Cart cart = response.getBody();
//		Assert.assertTrue(cart.getItems().contains(Utils.createNewItem1()));
//	}
//
//	/**
//	 * Test Add To Cart When Invalid Username Then Return Not Found
//	 */
//	@Test
//	public void testAddToCartWhenInvalidUsernameThenReturnNotFound() {
//		when(userRepository.findByUsername(USER_1)).thenReturn(Utils.createUser());
//		when(itemRepository.findById(1L)).thenReturn(Optional.of(Utils.createNewItem1()));
//		ModifyCartRequest request = new ModifyCartRequest();
//		request.setQuantity(1);
//
//		request.setUsername(USER_2);
//		ResponseEntity<Cart> response2 = cartController.addTocart(request);
//		Assert.assertEquals(404, response2.getStatusCodeValue());
//	}
//
//	/**
//	 * Test Add To Cart When Invalid Item Then Return Not Found
//	 */
//	@Test
//	public void testAddToCartWhenInvalidItemThenReturnNotFound() {
//		when(userRepository.findByUsername(USER_1)).thenReturn(Utils.createUser());
//		when(itemRepository.findById(1L)).thenReturn(Optional.of(Utils.createNewItem1()));
//		ModifyCartRequest request = new ModifyCartRequest();
//		request.setQuantity(1);
//
//		request.setUsername(USER_1);
//		request.setItemId(2L);
//		ResponseEntity<Cart> response3 = cartController.addTocart(request);
//		Assert.assertEquals(404, response3.getStatusCodeValue());
//	}
//
//	/**
//	 * Test Remove Cart When Valid Request Then Return Successfully
//	 */
//	@Test
//	public void testRemoveCartWhenValidRequestThenReturnSuccessfully() {
//		when(userRepository.findByUsername(USER_1)).thenReturn(Utils.createUser());
//		when(itemRepository.findById(2L)).thenReturn(Optional.of(Utils.createNewItem2()));
//		ModifyCartRequest request = new ModifyCartRequest();
//		request.setUsername(USER_1);
//		request.setItemId(2L);
//		request.setQuantity(1);
//
//		ResponseEntity<Cart> response = cartController.removeFromcart(request);
//		Assert.assertNotNull(response);
//		Assert.assertEquals(200, response.getStatusCodeValue());
//		Cart cart = response.getBody();
//		Assert.assertFalse(cart.getItems().contains(Utils.createNewItem2()));
//
//		request.setUsername("test 2");
//		ResponseEntity<Cart> response2 = cartController.removeFromcart(request);
//		Assert.assertEquals(404, response2.getStatusCodeValue());
//
//		request.setUsername("test");
//		request.setItemId(3L);
//		ResponseEntity<Cart> response3 = cartController.removeFromcart(request);
//		Assert.assertEquals(404, response3.getStatusCodeValue());
//	}
//
//	/**
//	 * Test Remove Cart When Invalid Username Then Return Not Found
//	 */
//	@Test
//	public void testRemoveCartWhenInvalidUsernameThenReturnNotFound() {
//		when(userRepository.findByUsername(USER_1)).thenReturn(Utils.createUser());
//		when(itemRepository.findById(2L)).thenReturn(Optional.of(Utils.createNewItem2()));
//		ModifyCartRequest request = new ModifyCartRequest();
//		request.setUsername(USER_2);
//		request.setItemId(2L);
//		request.setQuantity(1);
//
//		ResponseEntity<Cart> response2 = cartController.removeFromcart(request);
//		Assert.assertEquals(404, response2.getStatusCodeValue());
//	}
//
//	/**
//	 * Test Remove Cart When Invalid Item Then Return Not Found
//	 */
//	@Test
//	public void testRemoveCartWhenInvalidItemThenReturnNotFound() {
//		when(userRepository.findByUsername(USER_1)).thenReturn(Utils.createUser());
//		when(itemRepository.findById(1L)).thenReturn(Optional.of(Utils.createNewItem2()));
//		ModifyCartRequest request = new ModifyCartRequest();
//		request.setUsername(USER_1);
//		request.setItemId(2L);
//		request.setQuantity(1);
//
//		ResponseEntity<Cart> response3 = cartController.removeFromcart(request);
//		Assert.assertEquals(404, response3.getStatusCodeValue());
//	}
//
//	/**
//	 * Test Get All Items When Valid Request Then Return Successfully
//	 */
//	@Test
//	public void testGetAllItemsWhenValidRequestThenReturnSucessfully() {
//		when(itemRepository.findAll()).thenReturn(Utils.createListItems());
//		ResponseEntity<List<Item>> response = itemController.getItems();
//		Assert.assertNotNull(response);
//		Assert.assertEquals(200, response.getStatusCodeValue());
//		Assert.assertArrayEquals(Utils.createListItems().toArray(), response.getBody().toArray());
//	}
//
//	/**
//	 * Test Get Item By Id When Valid Request Then Return Successfully
//	 */
//	@Test
//	public void testGetItemByIdWhenValidRequestThenReturnSucessfully() {
//		when(itemRepository.findById(1L)).thenReturn(Optional.of(Utils.createNewItem1()));
//		ResponseEntity<Item> response = itemController.getItemById(1L);
//		Assert.assertNotNull(response);
//		Assert.assertEquals(200, response.getStatusCodeValue());
//		Assert.assertEquals(response.getBody().getId(), Utils.createNewItem1().getId());
//	}
//
//	/**
//	 * Test Submit Order When Valid Request Then Return Successfully
//	 */
//	@Test
//	public void testSubmitOrderWhenValidRequestThenReturnSuccessfully(){
//		when(userRepository.findByUsername(USER_1)).thenReturn(Utils.createUser());
//
//		final ResponseEntity<UserOrder> response = orderController.submit(USER_1);
//		Assert.assertNotNull(response);
//		Assert.assertEquals(200,response.getStatusCodeValue());
//		UserOrder order = response.getBody();
//		Assert.assertEquals(order.getUser().getId(),Utils.createUser().getId());
//		Assert.assertTrue(order.getItems().contains(Utils.createNewItem1()));
//	}
//
//	/**
//	 * Test Submit Order When Invalid Username Then Return Not Found
//	 */
//	@Test
//	public void testSubmitOrderWhenInvalidUserNameThenReturnNotFound(){
//		when(userRepository.findByUsername(USER_1)).thenReturn(Utils.createUser());
//
//		final ResponseEntity<UserOrder> response = orderController.submit(USER_2);
//		Assert.assertEquals(404,response.getStatusCodeValue());
//	}
//
//	/**
//	 * Test Get Orders For User When Valid Request Then Return Successfully
//	 */
//	@Test
//	public void testGetOrdersForUserWhenValidRequestThenReturnSuccessfully() {
//		User user = Utils.createUser();
//		when(userRepository.findByUsername(USER_1)).thenReturn(user);
//		when(orderRepository.findByUser(user)).thenReturn(Utils.createListUserOrders());
//
//		ResponseEntity<List<UserOrder>> response = orderController.getOrdersForUser(USER_1);
//		Assert.assertNotNull(response);
//		Assert.assertEquals(200, response.getStatusCodeValue());
//		Assert.assertFalse(response.getBody().isEmpty());
//	}
//
//	/**
//	 * Test Get Orders For User When Invalid Username Then Return Not Found
//	 */
//	@Test
//	public void testGetOrdersForUserWhenInvalidUsernameThenReturnNotFound() {
//		User user = Utils.createUser();
//		when(userRepository.findByUsername(USER_1)).thenReturn(user);
//		when(orderRepository.findByUser(user)).thenReturn(Utils.createListUserOrders());
//
//		ResponseEntity<List<UserOrder>> response = orderController.getOrdersForUser( USER_2);
//		Assert.assertEquals(404, response.getStatusCodeValue());
//	}
//
//	/**
//	 * Test Get Orders For User When Valid Request Then Return Successfully
//	 */
//	@Test
//	public void testCreateUserWhenValidRequestThenReturnSuccessfully() {
//		CreateUserRequest request = new CreateUserRequest();
//		request.setUsername(USER_1);
//		request.setPassword(DEFAULT_PASSWORD);
//		request.setConfirmPassword(DEFAULT_PASSWORD);
//
//		ResponseEntity<User> response = userController.createUser(request);
//		Assert.assertNotNull(response);
//		Assert.assertEquals(200, response.getStatusCodeValue());
//
//		User user = response.getBody();
//		Assert.assertNotNull(user);
//		Assert.assertEquals(0, user.getId());
//		Assert.assertEquals(USER_1, user.getUsername());
//	}
//
//	/**
//	 * Test Find User By Username For User When Valid Request Then Return Successfully
//	 */
//	@Test
//	public void testFindUserByUsernameWhenValidRequestThenReturnSuccessfully(){
//		CreateUserRequest request = new CreateUserRequest();
//		request.setUsername(USER_1);
//		request.setPassword(DEFAULT_PASSWORD);
//		request.setConfirmPassword(DEFAULT_PASSWORD);
//
//		final ResponseEntity<User> response = userController.createUser(request);
//		Assert.assertEquals(200,response.getStatusCodeValue());
//
//		when(userRepository.findByUsername(USER_1)).thenReturn(response.getBody());
//		final ResponseEntity<User> response2 = userController.findByUserName(USER_1);
//		Assert.assertNotNull(response2);
//		Assert.assertEquals(200,response2.getStatusCodeValue());
//		Assert.assertEquals(USER_1,response2.getBody().getUsername());
//
//	}
//	/**
//	 * Test Find User By Username For User When Invalid Username Then Return Not Found
//	 */
//	@Test
//	public void testFindByUserNameForUserWhenInvalidUsernameThenReturnNotFound(){
//		final ResponseEntity<User> response1 = userController.findByUserName(USER_1);
//		Assert.assertNotNull(response1);
//		Assert.assertEquals(404,response1.getStatusCodeValue());
//		Assert.assertNull(response1.getBody());
//
//	}
//	@Test
//	public void testFindUserByIdWhenValidUsernameThenReturnSuccessfully(){
//		CreateUserRequest request = new CreateUserRequest();
//		request.setUsername(USER_1);
//		request.setPassword(DEFAULT_PASSWORD);
//		request.setConfirmPassword(DEFAULT_PASSWORD);
//
//		final ResponseEntity<User> response1 = userController.createUser(request);
//		Assert.assertEquals(200,response1.getStatusCodeValue());
//
//		when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(response1.getBody()));
//		final ResponseEntity<User> response2 = userController.findById(1L);
//		Assert.assertNotNull(response2);
//		Assert.assertEquals(200,response2.getStatusCodeValue());
//		Assert.assertEquals(USER_1,response2.getBody().getUsername());
//
//	}
//
//	/**
//	 * Test Find By Id User When Invalid Username Then Return Not Found
// 	 */
//	@Test
//	public void testFindByIdUserWhenInvalidUsernameThenReturnNotFound(){
//
//		final ResponseEntity<User> response1 = userController.findById(1L);
//		Assert.assertNotNull(response1);
//		Assert.assertEquals(404,response1.getStatusCodeValue());
//		Assert.assertNull(response1.getBody());
//
//	}
//
//	@Test
//	public void testCreateUserWhenUnmatchedPasswordThenReturnBadRequest(){
//		CreateUserRequest request = new CreateUserRequest();
//		request.setUsername(USER_1);
//		request.setPassword(DEFAULT_PASSWORD);
//		request.setConfirmPassword("abcdefgh");
//
//		final ResponseEntity<User> response = userController.createUser(request);
//
//		Assert.assertNotNull(response);
//		Assert.assertEquals(400,response.getStatusCodeValue());
//	}
}