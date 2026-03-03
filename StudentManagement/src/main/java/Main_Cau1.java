package studentmanagement;


public class Main_Cau1 {
    public static void main(String[] args) {
        System.out.println("===== CÂU 1: QUẢN LÝ SINH VIÊN VỚI GENERIC =====\n");
        
       
        StudentManager<Student> studentManager = new StudentManager<>();
        
        
        System.out.println("Đang thêm sinh viên vào danh sách...");
        
        Student sv1 = new Student("SV001", "Daoxuanthanh", 3.5);
        Student sv2 = new Student("SV002", "VanViet", 3.8);
        Student sv3 = new Student("SV003", "MinhTu", 3.2);
        Student sv4 = new Student("SV004", "TienThanh", 2.9);
        Student sv5 = new Student("SV005", "HoagNam", 3.6);
        
        studentManager.add(sv1);
        studentManager.add(sv2);
        studentManager.add(sv3);
        studentManager.add(sv4);
        studentManager.add(sv5);
        
        System.out.println("Đã thêm " + studentManager.getAll().size() + " sinh viên!\n");
        

        System.out.println("===== DANH SÁCH SINH VIÊN =====");
       
        System.out.println("\n--- Duyệt bằng for-each ---");
        for (Student student : studentManager.getAll()) {
            System.out.println(student);
        }
        
        System.out.println("\n--- Duyệt bằng forEach với Lambda ---");
        studentManager.getAll().forEach(student -> System.out.println(student));
        
        System.out.println("\n--- Duyệt bằng method reference ---");
        studentManager.getAll().forEach(System.out::println);
        
        System.out.println("\n===== THÔNG TIN CHI TIẾT SINH VIÊN =====");
        for (int i = 0; i < studentManager.getAll().size(); i++) {
            Student sv = studentManager.getAll().get(i);
            System.out.println("Sinh viên thứ " + (i + 1) + ":");
            System.out.println("  Mã số: " + sv.getId());
            System.out.println("  Họ tên: " + sv.getName());
            System.out.println("  Điểm GPA: " + sv.getGpa());
            System.out.println("  Xếp loại: " + getRank(sv.getGpa()));
            System.out.println();
        }
    }
    

    public static String getRank(double gpa) {
        if (gpa >= 3.6) {
            return "Xuất sắc";
        } else if (gpa >= 3.2) {
            return "Giỏi";
        } else if (gpa >= 2.5) {
            return "Khá";
        } else if (gpa >= 2.0) {
            return "Trung bình";
        } else {
            return "Yếu";
        }
    }
}