package com.company.restaurant.application.data.adder;

import com.company.restaurant.application.data.adder.proto.ObjectAdderProto;
import com.company.restaurant.application.data.chooser.proto.ObjectChooser;
import com.company.restaurant.controllers.KitchenController;
import com.company.restaurant.model.CookedCourse;
import com.company.restaurant.model.Course;
import com.company.restaurant.model.Employee;
import com.company.util.Util;

/**
 * Created by Yevhen on 29.05.2016.
 */
public class CookedCourseAdder extends ObjectAdderProto<CookedCourse> {
    private static final String ENTER_WEIGHT_MESSAGE = "Please, enter course weight";
    private static final String ALREADY_COOKED_COURSES_MESSAGE = "Already cooked courses:";

    private KitchenController kitchenController;
    private ObjectChooser<Course> courseChooser;
    private ObjectChooser<Employee> employeeChooser;

    public void setKitchenController(KitchenController kitchenController) {
        this.kitchenController = kitchenController;
    }

    public void setCourseChooser(ObjectChooser<Course> courseChooser) {
        this.courseChooser = courseChooser;
    }

    public void setEmployeeChooser(ObjectChooser<Employee> employeeChooser) {
        this.employeeChooser = employeeChooser;
    }

    @Override
    protected CookedCourse addObject() {
        CookedCourse result = null;

        Course course = courseChooser.chooseObjectFromList();
        if (course != null) {
            Employee employee = employeeChooser.chooseObjectFromList();
            if (employee != null) {
                Float weight = Util.readInputPositiveFloat(ENTER_WEIGHT_MESSAGE, true);
                if (weight != null) {
                    result = kitchenController.addCookedCourse(course, employee, weight);
                    dataHasBeenSuccessfullyAddedMessage();
                }
            }
        }

        return result;
    }

    @Override
    protected String getExistingDataMessage() {
        return ALREADY_COOKED_COURSES_MESSAGE;
    }
}
