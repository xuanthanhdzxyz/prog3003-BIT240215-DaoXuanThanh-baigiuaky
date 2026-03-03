package studentmanagement;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


public class Main_Cau2 {
    
    
    public static double calculateAverageGpa(List<Student> students) {
        
        if (students == null || students.isEmpty()) {
            System.out.println("Danh sach sinh vien rong!");
            return 0.0;
        }
        
        double sum = 0;
        for (Student student : students) {
            sum += student.getGpa();
        }
        
        double average = sum / students.size();
        return Math.round(average * 100.0) / 100.0; 
    }
    
    
    public static void inputStudents(StudentManager<Student> studentManager, Scanner scanner) {
        System.out.print("Nhap so luong sinh vien: ");
        int n = Integer.parseInt(scanner.nextLine());
        
        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Nhap sinh vien thu " + (i + 1) + " ---");
            
            System.out.print("Nhap ma SV: ");
            String id = scanner.nextLine();
            
            System.out.print("Nhap ten SV: ");
            String name = scanner.nextLine();
            
            double gpa = 0;
            while (true) {
                try {
                    System.out.print("Nhap diem GPA (0-4): ");
                    gpa = Double.parseDouble(scanner.nextLine());
                    if (gpa >= 0 && gpa <= 4) {
                        break;
                    } else {
                        System.out.println("Diem GPA phai tu 0 den 4!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Vui long nhap so hop le!");
                }
            }
            
            studentManager.add(new Student(id, name, gpa));
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager<Student> studentManager = new StudentManager<>();
        
        System.out.println("===== CAU 2: XU LY BAT DONG BO VOI COMPLETABLEFUTURE =====\n");
        
       
        inputStudents(studentManager, scanner);
        
        List<Student> students = studentManager.getAll();
        
        
        System.out.println("\nDanh sach sinh vien:");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i));
        }
        System.out.println();
        
        
        System.out.println("BAT DAU TINH TOAN BAT DONG BO...");
        System.out.println("Thoi gian: " + java.time.LocalTime.now());
        System.out.println();
        
        
        CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> {
            try {
                
                System.out.println("Thread xu ly: " + Thread.currentThread().getName());
                
                
                System.out.println("Dang xu ly tinh toan... (gia lap do tre 1 giay)");
                Thread.sleep(1000);
                
               
                double result = calculateAverageGpa(students);
                
                System.out.println("Hoan thanh tinh toan!");
                return result;
                
            } catch (InterruptedException e) {
                
                System.out.println("Loi: Qua trinh tinh toan bi gian doan!");
                throw new RuntimeException("Loi trong qua trinh xu ly: " + e.getMessage());
            }
        });
        
        
        future.thenAccept(result -> {
            System.out.println("\n=== KET QUA ===");
            System.out.println("Thoi gian nhan ket qua: " + java.time.LocalTime.now());
            System.out.println("Diem trung binh GPA cua " + students.size() + " sinh vien la: " + result);
            System.out.println("Xu ly hoan tat!");
        });
        
        
        future.exceptionally(ex -> {
            System.out.println("\n=== LOI ===");
            System.out.println("Co loi xay ra trong qua trinh xu ly: " + ex.getMessage());
            return null;
        });
        
        
        System.out.println("\nLUONG CHINH DANG CHAY...");
        System.out.println("Luong chinh: " + Thread.currentThread().getName());
        
        for (int i = 1; i <= 5; i++) {
            try {
                Thread.sleep(300); // Nghi 0.3 giay
                System.out.println("Luong chinh dang lam viec khac... (buoc " + i + ")");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        
        System.out.println("\n--- KIEM TRA TRANG THAI ---");
        System.out.println("Future da hoan thanh? " + future.isDone());
        System.out.println("Future da bi huy? " + future.isCancelled());
        
        
        try {
            future.get(2, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("Khong the lay ket qua: " + e.getMessage());
        }
        
        System.out.println("\nChuong trinh ket thuc!");
        scanner.close();
    }
}