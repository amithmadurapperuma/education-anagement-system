package com.developersstack.edumanager.db;

import com.developersstack.edumanager.modle.*;
import com.developersstack.edumanager.util.security.PasswordManager;

import java.util.ArrayList;

public class Database {
    public static ArrayList<User> userTable = new ArrayList();
    public static ArrayList<Student> studentTable = new ArrayList();

    public static  ArrayList<Teacher> teacherTable = new ArrayList<>();

    public static ArrayList<Intake> intakeTable = new ArrayList<>();

    public static ArrayList<Program> programTable = new ArrayList<>();

    public static ArrayList<Register> registerTable = new ArrayList<>();


    static {
        userTable.add(new User(
                "Amith",
                "Madurapperuma",
                "a",
                new PasswordManager().encrypt("a")
        ));
    }

}
