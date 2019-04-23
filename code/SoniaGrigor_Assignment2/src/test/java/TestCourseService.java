import model.business.course.CourseService;
import model.business.course.CourseServicePostgreSQL;
import model.persistence.entity.Course;
import model.persistence.entity.Enrollment;
import model.persistence.entity.User;
import model.persistence.entity.UserCourse;
import model.persistence.repository.course.CourseRepository;
import model.persistence.repository.user.UserCourseRepository;
import model.persistence.repository.user.UserCourseRepositoryPostgreSQL;
import model.persistence.repository.user.UserRepository;
import model.persistence.repository.user.UserRepositoryPostgreSQL;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class TestCourseService {


    public CourseService courseService;

    @Mock
    public UserRepository userRepository;
    @Mock
    public CourseRepository courseRepository;
    @Mock
    public UserCourseRepository userCourseRepository;

    @Mock
    public User user;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userRepository=new UserRepositoryPostgreSQL();
        courseService=new CourseServicePostgreSQL();
        userCourseRepository=new UserCourseRepositoryPostgreSQL();
        courseService=new CourseServicePostgreSQL();
    }

    @Test
    public void testGetMyCourses() {
        user = new User();
        user.setId(100);
        user.setName("User Tested");
        user.setUsername("username");
        user.setPassword("password");

        List<Course> courseList = new ArrayList<>();
        Course course = new Course();
        course.setId(1);
        course.setName("Matematica");
        courseList.add(course);

        List<UserCourse> userCourseList = new ArrayList<>();
        UserCourse userCourse = new UserCourse();
        userCourse.setId(1);
        userCourse.setCourseId(1);
        userCourse.setUserId(100);
        userCourse.setGrade(5);
        userCourseList.add(userCourse);

        when(userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword())).thenReturn(user);
        when(courseRepository.getAll()).thenReturn(courseList);
        when(userCourseRepository.getAll()).thenReturn(userCourseList);

        List<Enrollment> enrollmentList = new ArrayList<Enrollment>(Collections.singleton(new Enrollment(courseList.get(0), userCourse.getGrade())));
        assertEquals(enrollmentList, courseService.getMyCourses(100));


    }
}
