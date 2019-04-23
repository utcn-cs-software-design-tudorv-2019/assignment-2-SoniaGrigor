import model.business.student.StudentService;
import model.business.student.StudentServicePostgreSQL;
import model.persistence.entity.User;
import model.persistence.repository.student.StudentRepository;
import model.persistence.repository.student.StudentRepositoryPostgreSQL;
import model.persistence.repository.user.UserCourseRepository;
import model.persistence.repository.user.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestStudentService {

    public StudentService studentService;
    public StudentRepository studentRepository;
    public UserRepository userRepository;
    public UserCourseRepository userCourseRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        studentService=mock(StudentServicePostgreSQL.class);
        studentRepository=mock(StudentRepositoryPostgreSQL.class);
    }

    @After
    public void tearDown(){
        studentService=null;
        studentRepository=null;
    }
    @Test
    public void testRemoveById(){
        when(studentService.removeById(1)).thenCallRealMethod();
        when(studentRepository.deleteById(1)).thenReturn(true);

        assertTrue(studentService.removeById(anyInt()));
    }

    @Test
    public void testEnrollCourse(){
        User user;
        user=new User();
        user.setId(2);
        user.setUsername("Test");
        user.setPassword("password");
        when(studentRepository.enrollCourse(any())).thenReturn(true);

        assertTrue(studentService.enrollCourse(2, 1));
    }
}
