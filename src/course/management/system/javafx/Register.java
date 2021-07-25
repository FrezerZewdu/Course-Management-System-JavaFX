/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package course.management.system.javafx;

/**
 *
 * @author Sebagadis Kahsay
 */
public class Register {
     private static String n;
     private static String p;
     public static String getusername()
     {
         return n;
     }
     public static void setusername(String username)
     {
         Register.n = username;
     }
      public static String getPassword()
     {
         return p;
     }
     public static void setPassword(String password)
     {
         Register.p = password;
     }
    
}
