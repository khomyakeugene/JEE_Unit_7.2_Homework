package com.company.restaurant.controllers;

import com.company.restaurant.Util;
import com.company.restaurant.model.*;
import com.company.util.DataIntegrityException;
import com.company.util.ObjectService;
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

    private static MenuController menuController;
    private static TableController tableController;
    private static EmployeeController employeeController;
    private static WarehouseController warehouseController;
    private static KitchenController kitchenController;
    private static OrderController orderController;
    private static CourseController courseController;

    private static int closedOrderId;
    private static OrderView closedOrder;
    private static String closedOrderCourseName1;
    private static Course closedOrderCourse1;
    private static String closedOrderCourseName2;
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
        testCourse.setCategoryId(courseCategoryId());
        testCourse.setName(Util.getRandomString());
        testCourse.setWeight(Util.getRandomFloat());
        testCourse.setCost(Util.getRandomFloat());

        testCourse = courseController.addCourse(testCourse);

        return testCourse;
    }

    private static void delTestCourse() {
        courseController.delCourse(testCourse);
    }

    private static void prepareClosedOrder() throws Exception {
        OrderView orderView = new OrderView();
        orderView.setTableId(tableId());
        orderView.setEmployeeId(employeeId());
        orderView.setOrderNumber(Util.getRandomString());
        orderView = orderController.addOrder(orderView);
        closedOrderId = orderView.getOrderId();

        // Courses for closed order ----------------------------
        closedOrderCourseName1 = Util.getRandomString();
        closedOrderCourse1 = new Course();
        closedOrderCourse1.setCategoryId(courseCategoryId());
        closedOrderCourse1.setName(closedOrderCourseName1);
        closedOrderCourse1.setWeight(Util.getRandomFloat());
        closedOrderCourse1.setCost(Util.getRandomFloat());
        closedOrderCourse1 = courseController.addCourse(closedOrderCourse1);

        closedOrderCourseName2 = Util.getRandomString();
        closedOrderCourse2 = new Course();
        closedOrderCourse2.setCategoryId(courseCategoryId());
        closedOrderCourse2.setName(closedOrderCourseName2);
        closedOrderCourse2.setWeight(Util.getRandomFloat());
        closedOrderCourse2.setCost(Util.getRandomFloat());
        closedOrderCourse2 = courseController.addCourse(closedOrderCourse2);
        // ----------

        orderController.addCourseToOrder(orderView, closedOrderCourse1, 1);

        closedOrder = orderController.closeOrder(orderView);
    }

    private static void clearClosedOrder() throws Exception {
        OrderView orderView = orderController.findOrderById(closedOrderId);
        // Manually change order state to "open"
        orderView = orderController.updOrderState(orderView, "A");

        // Delete "open" order
        orderController.delOrder(orderView);

        // Delete course for closed order
        courseController.delCourse(closedOrderCourseName1);
        courseController.delCourse(closedOrderCourseName2);
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

        assertTrue(ObjectService.isEqualByGetterValuesStringRepresentation(jobPosition,
                employeeController.findJobPositionByName(jobPosition.getName())));
        assertTrue(ObjectService.isEqualByGetterValuesStringRepresentation(jobPosition,
                employeeController.findJobPositionById(jobPosition.getId())));

        employeeController.delJobPosition(name);
        assertTrue(employeeController.findJobPositionByName(name) == null);
        // Test delete of non-existent data
        employeeController.delJobPosition(name);

        for (JobPosition jP : employeeController.findAllJobPositions()) {
            System.out.println("Job position Id :" + jP.getId() +
                    ", Job position name :" + jP.getName());
        }
    }

    @Test(timeout = 2000)
    public void addFindDelEmployeeTest() throws Exception {
        String firstName = Util.getRandomString();
        String secondName = Util.getRandomString();
        Employee employee = new Employee();
        employee.setJobPositionId(jobPositionId());
        employee.setFirstName(firstName);
        employee.setSecondName(secondName);
        employee.setPhoneNumber(Util.getRandomString());
        employee.setSalary(Util.getRandomFloat());

        employee = employeeController.addEmployee(employee);
        int employeeId = employee.getEmployeeId();

        // Select test <employee> and check
        Employee employeeByFirstName = employeeController.findEmployeeByFirstName(firstName).get(0);
        assertTrue(ObjectService.isEqualByGetterValuesStringRepresentation(employee, employeeByFirstName));

        Employee employeeBySecondName = employeeController.findEmployeeBySecondName(secondName).get(0);
        assertTrue(ObjectService.isEqualByGetterValuesStringRepresentation(employee, employeeBySecondName));

        Employee employeeByFirstAndSecondName =
                employeeController.findEmployeeByFirstAndSecondName(firstName, secondName).get(0);
        assertTrue(ObjectService.isEqualByGetterValuesStringRepresentation(employee, employeeByFirstAndSecondName));

        Employee employeeById = employeeController.findEmployeeById(employeeId);
        assertTrue(ObjectService.isEqualByGetterValuesStringRepresentation(employee, employeeById));

        employeeController.delEmployee(employee);
        assertTrue(employeeController.findEmployeeById(employeeId) == null);
    }

    @Test(timeout = 2000)
    public void addFindDelCourseCategoryTest() throws Exception {
        String name = Util.getRandomString();
        CourseCategory courseCategory = courseController.addCourseCategory(name);

        assertTrue(ObjectService.isEqualByGetterValuesStringRepresentation(courseCategory,
                courseController.findCourseCategoryByName(courseCategory.getName())));
        assertTrue(ObjectService.isEqualByGetterValuesStringRepresentation(courseCategory,
                courseController.findCourseCategoryById(courseCategory.getId())));

        courseController.delCourseCategory(name);
        assertTrue(courseController.findCourseCategoryByName(name) == null);
        // Test delete of non-existent data
        courseController.delCourseCategory(name);
    }

    @Test(timeout = 2000)
    public void addFindDelCourseTest() throws Exception {
        String name = Util.getRandomString();
        Course course = new Course();
        course.setCategoryId(courseCategoryId());
        course.setName(name);
        course.setWeight(Util.getRandomFloat());
        course.setCost(Util.getRandomFloat());
        course = courseController.addCourse(course);

        assertTrue(ObjectService.isEqualByGetterValuesStringRepresentation(course,
                courseController.findCourseByName(course.getName())));
        assertTrue(ObjectService.isEqualByGetterValuesStringRepresentation(course,
                courseController.findCourseById(course.getCourseId())));

        courseController.delCourse(name);
        assertTrue(courseController.findCourseByName(name) == null);
        // Test delete by "the whole object"
        course = courseController.addCourse(course);
        assertTrue(ObjectService.isEqualByGetterValuesStringRepresentation(course,
                courseController.findCourseByName(name)));
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

        Menu menuByName = menuController.findMenuByName(name);
        assertTrue(ObjectService.isEqualByGetterValuesStringRepresentation(menu, menuByName));
        assertTrue(ObjectService.isEqualByGetterValuesStringRepresentation(menu,
                menuController.findMenuById(menu.getId())));

        // Courses in menu ----------------------------
        String courseName1 = Util.getRandomString();
        Course course1 = new Course();
        course1.setCategoryId(courseCategoryId());
        course1.setName(courseName1);
        course1.setWeight(Util.getRandomFloat());
        course1.setCost(Util.getRandomFloat());
        course1 = courseController.addCourse(course1);

        String courseName2 = Util.getRandomString();
        Course course2 = new Course();
        course2.setCategoryId(courseCategoryId());
        course2.setName(courseName2);
        course2.setWeight(Util.getRandomFloat());
        course2.setCost(Util.getRandomFloat());
        course2 = courseController.addCourse(course2);

        menuController.addCourseToMenu(menu, course1);
        menuController.addCourseToMenu(menu, course2);

        for (MenuCourseView menuCourseList : menuController.findMenuCourses(menu)) {
            menuController.findMenuCourseByCourseId(menu, menuCourseList.getCourseId());
            System.out.println(menuCourseList.getCourseName() + ": " + menuCourseList.getCourseCategoryName());
        }


        menuController.delCourseFromMenu(menu, course1);
        menuController.delCourseFromMenu(menu, course2);

        courseController.delCourse(courseName1);
        courseController.delCourse(courseName2);
        // ----------------------------

        for (Menu m : menuController.findAllMenus()) {
            System.out.println("menu_id: " + m.getId() + ", name: " + m.getName());
        }

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

        assertTrue(ObjectService.isEqualByGetterValuesStringRepresentation(table,
                tableController.findTableByNumber(table.getNumber())));
        assertTrue(ObjectService.isEqualByGetterValuesStringRepresentation(table,
                tableController.findTableById(table.getTableId())));

        tableController.delTable(table);
        assertTrue(tableController.findTableByNumber(table.getNumber()) == null);
    }

    @Test (timeout = 2000)
    public void addFindDelOrderTest() throws Exception {
        OrderView orderView = new OrderView();
        orderView.setTableId(tableId());
        orderView.setEmployeeId(employeeId());
        orderView.setOrderNumber(Util.getRandomString());
        orderView = orderController.addOrder(orderView);
        int orderId = orderView.getOrderId();

        OrderView orderById = orderController.findOrderById(orderId);
        // Just check of successful retrieving from database,  without "full comparing"!!!
        // Because, at least field <order_datetime> is filling by default (as a current timestamp) on the database level
        assertTrue(orderById != null);

        // Courses in order ----------------------------
        String courseName1 = Util.getRandomString();
        Course course1 = new Course();
        course1.setCategoryId(courseCategoryId());
        course1.setName(courseName1);
        course1.setWeight(Util.getRandomFloat());
        course1.setCost(Util.getRandomFloat());
        course1 = courseController.addCourse(course1);

        String courseName2 = Util.getRandomString();
        Course course2 = new Course();
        course2.setCategoryId(courseCategoryId());
        course2.setName(courseName2);
        course2.setWeight(Util.getRandomFloat());
        course2.setCost(Util.getRandomFloat());
        course2 = courseController.addCourse(course2);

        orderController.addCourseToOrder(orderView, course1, 3);
        orderController.addCourseToOrder(orderView, course2, 2);

        for (OrderCourseView orderCourseView : orderController.findAllOrderCourses(orderView)) {
            orderController.findOrderCourseByCourseId(orderView, orderCourseView.getCourseId());
            System.out.println(orderCourseView.getCourseName() + " : " + orderCourseView.getCourseCost());
        }

        orderController.takeCourseFromOrder(orderView, course1, 2);
        orderController.takeCourseFromOrder(orderView, course1);
        orderController.takeCourseFromOrder(orderView, course2, 2);

        courseController.delCourse(courseName1);
        courseController.delCourse(courseName2);
        // ----------------------------

        for (OrderView o : orderController.findAllOrders()) {
            System.out.println("Order id: " + o.getOrderId() + ", Order number: " + o.getOrderNumber());
        }

        for (OrderView o : orderController.findAllOpenOrders()) {
            System.out.println("Open order id: " + o.getOrderId() + ", Order number: " + o.getOrderNumber());
        }

        for (OrderView o : orderController.findAllClosedOrders()) {
            System.out.println("Closed order id: " + o.getOrderId() + ", Order number: " + o.getOrderNumber());
        }

        orderController.delOrder(orderView);
        assertTrue(orderController.findOrderById(orderId) == null);
    }

    @Test(timeout = 2000, expected = DataIntegrityException.class)
    public void closedOrderTest_1() throws Exception {
        orderController.delOrder(closedOrder);
    }

    @Test(timeout = 2000)
    public void closedOrderTest_2() throws Exception {
        orderController.addCourseToOrder(closedOrder, closedOrderCourse2, 1);
    }

    @Test(timeout = 2000)
    public void closedOrderTest_3() throws Exception {
        orderController.takeCourseFromOrder(closedOrder, closedOrderCourse1, 1);
    }

    @Test(timeout = 2000)
    public void addCookedCourse() throws Exception {
        kitchenController.addCookedCourse(testCourse, employee(), Util.getRandomFloat());

        for (CookedCourseView cookedCourseView : kitchenController.findAllCookedCourses()) {
            System.out.println(cookedCourseView.getCourseName() + " : " + cookedCourseView.getCookDatetime());
        }
    }

    @Test(timeout = 2000)
    public void addDelCookedCourse() throws Exception {
        Course testCourse = new Course();
        testCourse.setCategoryId(courseCategoryId());
        testCourse.setName(Util.getRandomString());
        testCourse.setWeight(Util.getRandomFloat());
        testCourse.setCost(Util.getRandomFloat());

        testCourse = courseController.addCourse(testCourse);

        CookedCourseView cookedCourseView = kitchenController.addCookedCourse(testCourse, employee(),
                Util.getRandomFloat());

        for (CookedCourseView cCV : kitchenController.findAllCookedCourses()) {
            System.out.println(cCV.getCourseName() + " : " + cCV.getCookDatetime());
        }

        kitchenController.delCookedCourse(cookedCourseView);
        courseController.delCourse(testCourse);
    }


    @Test(timeout = 10000)
    public void addFindDelWarehouseTest() throws Exception {
        for (Ingredient ingredient: warehouseController.findAllIngredients()) {
            for (Portion portion : warehouseController.findAllPortions()) {
                float amountToAdd = Util.getRandomFloat();
                warehouseController.addIngredientToWarehouse(ingredient, portion, amountToAdd);
                float amountToTake = Util.getRandomFloat();
                warehouseController.takeIngredientFromWarehouse(ingredient, portion, amountToTake);

                System.out.println("warehouseController.findPortionById(" + portion.getPortionId() + ") test ...");
                assertTrue(ObjectService.isEqualByGetterValuesStringRepresentation(portion,
                        warehouseController.findPortionById(portion.getPortionId())));

                // "Clear" warehouse position
                warehouseController.takeIngredientFromWarehouse(ingredient, portion, amountToAdd - amountToTake);
            }

            System.out.println("warehouseController.findIngredientById(" + ingredient.getId() + ") test ...");
            assertTrue(ObjectService.isEqualByGetterValuesStringRepresentation(ingredient,
                    warehouseController.findIngredientById(ingredient.getId())));

            System.out.println("Warehouse: " + ingredient.getName() + " : ");
            for (WarehouseView warehouseView : warehouseController.findIngredientInWarehouseByName(ingredient.getName())) {
                System.out.println(warehouseView.getPortionDescription() + ": " + warehouseView.getAmount());
            }
        }

        System.out.println("Warehouse all ingredients:");
        for (WarehouseView warehouseView : warehouseController.findAllWarehouseIngredients()) {
            System.out.println(warehouseView.getIngredientName() + ": " + warehouseView.getAmount());
        }
        System.out.println("Warehouse elapsing ingredients:");
        for (WarehouseView warehouseView : warehouseController.findAllElapsingWarehouseIngredients((float)500.0)) {
            System.out.println(warehouseView.getIngredientName() + ": " + warehouseView.getPortionDescription() + ": " +
                    warehouseView.getAmount());
        }
    }
}