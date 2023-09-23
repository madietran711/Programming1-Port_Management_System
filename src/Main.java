public class Main {
    public static void printHeader(String groupName, String[] studentNames) {
        System.out.println("COSC2081 GROUP ASSIGNMENT");
        System.out.println("CONTAINER PORT MANAGEMENT SYSTEM");
        System.out.println("Instructor: Mr. Minh Vu & Dr. Phong Ngo");
        System.out.println("Group: " + groupName);

        // Calculate the maximum name and student ID lengths
        int maxNameLength = 0;
        int maxStudentIDLength = 0;

        for (String studentName : studentNames) {
            String[] parts = studentName.split(", ");
            if (parts.length == 2) {
                String name = parts[1];
                String studentID = parts[0];
                maxNameLength = Math.max(maxNameLength, name.length());
                maxStudentIDLength = Math.max(maxStudentIDLength, studentID.length());
            }
        }

        // Calculate the table width based on the maximum lengths
        int tableWidth = maxNameLength + maxStudentIDLength + 8;

        // Print the table header
        String headerLine = "+-" + "-".repeat(maxNameLength) + "-+-" + "-".repeat(maxStudentIDLength) + "-+";
        System.out.println(headerLine);
        System.out.printf("| %-" + maxNameLength + "s | %-" + maxStudentIDLength + "s |\n", "Name", "Student ID");
        System.out.println(headerLine);

        // Print student names and IDs in the table
        for (String studentName : studentNames) {
            String[] parts = studentName.split(", ");
            if (parts.length == 2) {
                String name = parts[1];
                String studentID = parts[0];
                System.out.printf("| %-" + maxNameLength + "s | %-" + maxStudentIDLength + "s |\n", name, studentID);
            }
        }

        // Print the table footer
        System.out.println(headerLine);
    }

    public static void main(String[] args) {
        String groupName = "Coffee Addicts";
        String[] studentNames = {
                "s3979638, Tran Ha Phuong",
                "sXXXXXXX, Tran Nguyen Quoc An",
                "sXXXXXXX, Pham Trong Nghia",
                "sXXXXXXX, Dang Minh Triet",
        };

        printHeader(groupName, studentNames);
    }
}
