package com.company.restaurant.controllers;

import com.company.restaurant.dao.*;
import com.company.restaurant.model.*;
import com.company.util.DataIntegrityException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Yevhen on 20.05.2016.
 */
public abstract class RestaurantControllerTest {
    private final static String DUPLICATE_KEY_VALUE_VIOLATES_MESSAGE = "duplicate key value violates";

    private static CourseCategoryDao courseCategoryDao;
    private static JobPositionDao jobPositionDao;
    private static EmployeeDao employeeDao;
    private static TableDao tableDao;
    private static StateDao stateDao;

    private static MenuController menuController;
    private static TableController tableController;
    private static EmployeeController employeeController;
    private static WarehouseController warehouseController;
    private static KitchenController kitchenController;
    private static OrderController orderController;
    private static CourseController courseController;

    private static int closedOrderId;
    private static Order closedOrder;
    private static Course closedOrderCourse1;
    private static Course closedOrderCourse2;
    private static Course testCourse;

    private static Employee employee() {
        return employeeController.findAllEmployees().get(0);
    }

    private int jobPositionId() {
        return employeeController.findAllJobPositions().get(0).getId();
    }

    private static int courseCategoryId() {
        return courseController.findAllCourseCategories().get(0).getId();
    }

    private static int employeeId() {
        return employee().getEmployeeId();
    }

    private static int tableId() {
        return tableController.findAllTables().get(0).getTableId();
    }

    private static int lastTableId() {
        List<Table> tableList = tableController.findAllTables();

        return tableList.get(tableList.size()-1).getTableId();
    }

    private static Course prepareTestCourse() {
        testCourse = new Course();
        testCourse.setName(Util.getRandomString());
        testCourse.setWeight(Util.getRandomFloat());
        testCourse.setCost(Util.getRandomFloat());
        testCourse.setCourseCategory(courseCategoryDao.findCourseCategoryById(courseCategoryId()));

        testCourse = courseController.addCourse(testCourse);

        return testCourse;
    }

    private static void delTestCourse() {
        courseController.delCourse(testCourse);
    }

    private static void prepareClosedOrder() throws Exception {
        Order order = new Order();
        order.setOrderNumber(Util.getRandomString());
        order.setTable(tableDao.findTableById(tableId()));
        order.setWaiter(employeeDao.findEmployeeById(employeeId()));
        order.setState(stateDao.findStateByType("A"));
        order = orderController.addOrder(order);
        closedOrderId = order.getOrderId();

        // Courses for closed order ----------------------------
        closedOrderCourse1 = new Course();
        closedOrderCourse1.setName(Util.getRandomString());
        closedOrderCourse1.setWeight(Util.getRandomFloat());
        closedOrderCourse1.setCost(Util.getRandomFloat());
        closedOrderCourse1.setCourseCategory(courseCategoryDao.findCourseCategoryById(courseCategoryId()));
        closedOrderCourse1 = courseController.addCourse(closedOrderCourse1);

        closedOrderCourse2 = new Course();
        closedOrderCourse2.setName(Util.getRandomString());
        closedOrderCourse2.setWeight(Util.getRandomFloat());
        closedOrderCourse2.setCost(Util.getRandomFloat());
        closedOrderCourse2.setCourseCategory(courseCategoryDao.findCourseCategoryById(courseCategoryId()));
        closedOrderCourse2 = courseController.addCourse(closedOrderCourse2);
        // ----------

        orderController.addCourseToOrder(order, closedOrderCourse1);

        closedOrder = orderController.closeOrder(order);
    }

    private static void clearClosedOrder() throws Exception {
        Order order = orderController.findOrderById(closedOrderId);
        // Manually change order state to "open"
        order = orderController.updOrderState(order, "A");

        // Delete "open" order
        orderController.delOrder(order);

        // Delete course for closed order
        courseController.delCourse(closedOrderCourse1);
        courseController.delCourse(closedOrderCourse2);
    }

    protected static void initEnvironment() throws Exception {
        prepareTestCourse();
        prepareClosedOrder();
    }

    private static void tearDownEnvironment() throws Exception {
        delTestCourse();
        clearClosedOrder();
    }

    protected static void initDataSource(String configLocation) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(configLocation);

        courseCategoryDao = applicationContext.getBean(CourseCategoryDao.class);
        tableDao = applicationContext.getBean(TableDao.class);
        employeeDao = applicationContext.getBean(EmployeeDao.class);
        jobPositionDao = applicationContext.getBean(JobPositionDao.class);
        stateDao = applicationContext.getBean(StateDao.class);

        menuController = applicationContext.getBean(MenuController.class);
        tableController = applicationContext.getBean(TableController.class);
        employeeController = applicationContext.getBean(EmployeeController.class);
        warehouseController = applicationContext.getBean(WarehouseController.class);
        kitchenController = applicationContext.getBean(KitchenController.class);
        orderController = applicationContext.getBean(OrderController.class);
        courseController = applicationContext.getBean(CourseController.class);
    }


    @BeforeClass
    public static void setUpClass() throws Exception {
        initDataSource(null); // intentionally, to generate exception if use this code directly

        initEnvironment();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        tearDownEnvironment();
    }

    @Test(timeout = 2000)
    public void addFindDelJobPosition() throws Exception {
        String name = Util.getRandomString();
        JobPosition jobPosition = employeeController.addJobPosition(name);

        assertTrue(jobPosition.equals(employeeController.findJobPositionByName(jobPosition.getName())));
        assertTrue(jobPosition.equals(employeeController.findJobPositionById(jobPosition.getId())));

        employeeController.delJobPosition(name);
        assertTrue(employeeController.findJobPositionByName(name) == null);
        // Test delete of non-existent data
        employeeController.delJobPosition(name);

        employeeController.findAllJobPositions().forEach(System.out::println);
    }

    @Test(timeout = 2000)
    public void addFindDelEmployeeTest() throws Exception {
        String firstName = Util.getRandomString();
        String secondName = Util.getRandomString();
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setSecondName(secondName);
        employee.setPhoneNumber(Util.getRandomString());
        employee.setSalary(Util.getRandomFloat());
        employee.setJobPosition(jobPositionDao.findJobPositionById(jobPositionId()));
        employee = employeeController.addEmployee(employee);
        int employeeId = employee.getEmployeeId();

        // Select test <employee> and check
        assertTrue(employee.equals(employeeController.findEmployeeByFirstName(firstName).get(0)));
        assertTrue(employee.equals(employeeController.findEmployeeBySecondName(secondName).get(0)));
        assertTrue(employee.equals(employeeController.findEmployeeByFirstAndSecondName(
                firstName, secondName).get(0)));

        Employee employeeById = employeeController.findEmployeeById(employeeId);
        assertTrue(employee.equals(employeeById));

        employeeController.delEmployee(employee);
        assertTrue(employeeController.findEmployeeById(employeeId) == null);
    }

    @Test(timeout = 2000)
    public void addFindDelCourseCategoryTest() throws Exception {
        String name = Util.getRandomString();
        CourseCategory courseCategory = courseController.addCourseCategory(name);

        assertTrue(courseCategory.equals(courseController.findCourseCategoryByName(courseCategory.getName())));
        assertTrue(courseCategory.equals(courseController.findCourseCategoryById(courseCategory.getId())));

        courseController.delCourseCategory(name);
        assertTrue(courseController.findCourseCategoryByName(name) == null);
        // Test delete of non-existent data
        courseController.delCourseCategory(name);
    }

    @Test(timeout = 2000)
    public void addFindDelCourseTest() throws Exception {
        String name = Util.getRandomString();
        Course course = new Course();
        course.setName(name);
        course.setWeight(Util.getRandomFloat());
        course.setCost(Util.getRandomFloat());
        course.setCourseCategory(courseCategoryDao.findCourseCategoryById(courseCategoryId()));
        course = courseController.addCourse(course);

        assertTrue(course.equals(courseController.findCourseByName(course.getName())));
        assertTrue(course.equals(courseController.findCourseById(course.getCourseId())));

        courseController.delCourse(name);
        assertTrue(courseController.findCourseByName(name) == null);
        // Test delete by "the whole object"
        course = courseController.addCourse(course);
        assertTrue(course.equals(courseController.findCourseByName(name)));
        courseController.delCourse(course);
        assertTrue(courseController.findCourseByName(name) == null);
        // Test delete of non-existent data
        courseController.delCourse(name);

        // Whole course list
        courseController.findAllCourses();
    }

    @Test(timeout = 2000)
    public void addFindDelMenuTest() throws Exception {
        String name = Util.getRandomString();
        Menu menu = menuController.addMenu(name);

        assertTrue(menu.equals(menuController.findMenuByName(name)));
        assertTrue(menu.equals(menuController.findMenuById(menu.getId())));

        // Courses in menu ----------------------------
        Course course1 = new Course();
        course1.setName(Util.getRandomString());
        course1.setWeight(Util.getRandomFloat());
        course1.setCost(Util.getRandomFloat());
        course1.setCourseCategory(courseCategoryDao.findCourseCategoryById(courseCategoryId()));
        course1 = courseController.addCourse(course1);

        Course course2 = new Course();
        course2.setName(Util.getRandomString());
        course2.setWeight(Util.getRandomFloat());
        course2.setCost(Util.getRandomFloat());
        course2.setCourseCategory(courseCategoryDao.findCourseCategoryById(courseCategoryId()));
        course2 = courseController.addCourse(course2);

        menuController.addCourseToMenu(menu, course1);
        menuController.addCourseToMenu(menu, course2);

        for (Course course : menuController.findMenuCourses(menu)) {
            menuController.findMenuCourseByCourseId(menu, course.getCourseId());
            System.out.println(course);
        }

        menuController.delCourseFromMenu(menu, course1);
        menuController.delCourseFromMenu(menu, course2);

        courseController.delCourse(course1);
        courseController.delCourse(course2);
        // ----------------------------

        menuController.findAllMenus().forEach(System.out::println);

        menuController.delMenu(name);
        assertTrue(menuController.findMenuByName(name) == null);
    }

    @Test(timeout = 2000)
    public void addFindDelTableTest() throws Exception {
        Table table = new Table();
        table.setDescription(Util.getRandomString());
        boolean tableWasNotAdded = true;
        do {
            try {
                table.setNumber(tableController.findTableById(lastTableId()).getNumber() + Util.getRandomInteger());
                table = tableController.addTable(table);
                tableWasNotAdded = false;
            } catch (RuntimeException e) {
                //  Error "duplicate key value violates unique constraint "ak_u_table_number_table"" could be generated
                if (!e.getMessage().contains(DUPLICATE_KEY_VALUE_VIOLATES_MESSAGE)) {
                    throw new RuntimeException(e);

                }
            }
        } while (tableWasNotAdded);

        assertTrue(table.equals(tableController.findTableByNumber(table.getNumber())));
        assertTrue(table.equals(tableController.findTableById(table.getTableId())));

        tableController.delTable(table);
        assertTrue(tableController.findTableByNumber(table.getNumber()) == null);
    }

    @Test (timeout = 2000)
    public void addFindDelOrderTest() throws Exception {
        Order order = new Order();
        order.setOrderNumber(Util.getRandomString());
        order.setTable(tableDao.findTableById(tableId()));
        order.setWaiter(employeeDao.findEmployeeById(employeeId()));
        order.setState(stateDao.findStateByType("A"));
        order = orderController.addOrder(order);
        int orderId = order.getOrderId();

        // Just check of successful retrieving from database,  without "full comparing"!!!
        // Because, at least field <order_datetime> is filling by default (as a current timestamp) on the database level
        assertTrue(orderController.findOrderById(orderId) != null);

        // Courses in order ----------------------------
        Course course1 = new Course();
        course1.setName(Util.getRandomString());
        course1.setWeight(Util.getRandomFloat());
        course1.setCost(Util.getRandomFloat());
        course1.setCourseCategory(courseCategoryDao.findCourseCategoryById(courseCategoryId()));
        course1 = courseController.addCourse(course1);

        Course course2 = new Course();
        course2.setName(Util.getRandomString());
        course2.setWeight(Util.getRandomFloat());
        course2.setCost(Util.getRandomFloat());
        course2.setCourseCategory(courseCategoryDao.findCourseCategoryById(courseCategoryId()));
        course2 = courseController.addCourse(course2);

        orderController.addCourseToOrder(order, course1);
        orderController.addCourseToOrder(order, course2);

        for (Course course : orderController.findOrderCourses(order)) {
            orderController.findOrderCourseByCourseId(order, course.getCourseId());
            System.out.println(course);
        }

        orderController.takeCourseFromOrder(order, course1);
        orderController.takeCourseFromOrder(order, course1);
        orderController.takeCourseFromOrder(order, course2);

        courseController.delCourse(course1);
        courseController.delCourse(course2);
        // ----------------------------

        System.out.println("All orders:");
        orderController.findAllOrders().forEach(System.out::println);
        System.out.println("Open orders:");
        orderController.findAllOpenOrders().forEach(System.out::println);
        System.out.println("Closed orders:");
        orderController.findAllClosedOrders().forEach(System.out::println);

        orderController.delOrder(order);
        assertTrue(orderController.findOrderById(orderId) == null);
    }

    @Test(timeout = 2000, expected = DataIntegrityException.class)
    public void closedOrderTest_1() throws Exception {
        orderController.delOrder(closedOrder);
    }

    @Test(timeout = 2000)
    public void closedOrderTest_2() throws Exception {
        orderController.addCourseToOrder(closedOrder, closedOrderCourse2);
    }

    @Test(timeout = 2000)
    public void closedOrderTest_3() throws Exception {
        orderController.takeCourseFromOrder(closedOrder, closedOrderCourse1);
    }

    @Test(timeout = 2000)
    public void addDelCookedCourse() throws Exception {
        Course testCourse = new Course();
        testCourse.setName(Util.getRandomString());
        testCourse.setWeight(Util.getRandomFloat());
        testCourse.setCost(Util.getRandomFloat());
        testCourse.setCourseCategory(courseCategoryDao.findCourseCategoryById(courseCategoryId()));
        testCourse = courseController.addCourse(testCourse);

        CookedCourse cookedCourse = kitchenController.addCookedCourse(testCourse, employee(),
                Util.getRandomFloat());

        kitchenController.findAllCookedCourses().forEach(System.out::println);

        kitchenController.delCookedCourse(cookedCourse);
        courseController.delCourse(testCourse);
    }


    @Test(timeout = 30000)
    public void addFindDelWarehouseTest() throws Exception {
        for (Ingredient ingredient: warehouseController.findAllIngredients()) {
            for (Portion portion : warehouseController.findAllPortions()) {
                float amountToAdd = Util.getRandomFloat();
                warehouseController.addIngredientToWarehouse(ingredient, portion, amountToAdd);
                float amountToTake = Util.getRandomFloat();
                warehouseController.takeIngredientFromWarehouse(ingredient, portion, amountToTake);

                assertTrue(portion.equals(warehouseController.findPortionById(portion.getPortionId())));
                // "Clear" warehouse position
                warehouseController.takeIngredientFromWarehouse(ingredient, portion, amountToAdd - amountToTake);
            }

            assertTrue(ingredient.equals(warehouseController.findIngredientById(ingredient.getId())));

            for (Warehouse warehouse : warehouseController.findIngredientInWarehouseByName(ingredient.getName())) {
                System.out.println(warehouse);
            }
        }

        System.out.println("Warehouse all ingredients:");
        warehouseController.findAllWarehouseIngredients().forEach(System.out::println);

        System.out.println("Warehouse elapsing ingredients:");
        warehouseController.findAllElapsingWarehouseIngredients((float) 500.0).forEach(System.out::println);
    }
}