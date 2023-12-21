package com.service;

import com.member_information.StudentDo;
import com.paged_information.Demand;
import com.paged_information.TableDTO;

public interface StudentService {
    TableDTO retrieveStudents(Demand demand);

    boolean add(StudentDo studentDo);

    StudentDo getById(int selectedStudentId);

    boolean update(StudentDo studentDo);

    boolean delete(int[] selectedStudent);
}
