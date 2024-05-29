package com.aungmyatmoe.studentmanagement;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    private ObservableList<Student> students = FXCollections.observableArrayList(
        new Student("1", "John Doe", "Computer Science"),
        new Student("2", "Jane Smith", "Mathematics")
    );

    private TableView<Student> table = new TableView<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Student Management System");

        BorderPane root = new BorderPane();

        // Table View to display students
        TableColumn<Student, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());

        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<Student, String> courseColumn = new TableColumn<>("Course");
        courseColumn.setCellValueFactory(cellData -> cellData.getValue().courseProperty());

        table.setItems(students);
        table.getColumns().addAll(idColumn, nameColumn, courseColumn);

        VBox tableContainer = new VBox(table);
        tableContainer.setPadding(new Insets(20));
        root.setCenter(tableContainer);

        // Menu bar
        MenuBar menuBar = new MenuBar();
        Menu studentMenu = new Menu("Student");
        MenuItem addStudentMenuItem = new MenuItem("Add Student");
        MenuItem updateStudentMenuItem = new MenuItem("Update Student");
        MenuItem deleteStudentMenuItem = new MenuItem("Delete Student");
        studentMenu.getItems().addAll(addStudentMenuItem, updateStudentMenuItem, deleteStudentMenuItem);
        menuBar.getMenus().add(studentMenu);

        addStudentMenuItem.setOnAction(e -> showAddStudentForm());
        updateStudentMenuItem.setOnAction(e -> showUpdateStudentForm());
        deleteStudentMenuItem.setOnAction(e -> deleteStudent());

        root.setTop(menuBar);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAddStudentForm() {
        Stage stage = new Stage();
        stage.setTitle("Add Student");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label idLabel = new Label("ID:");
        TextField idField = new TextField();

        Label courseLabel = new Label("Course:");
        TextField courseField = new TextField();

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            students.add(new Student(idField.getText(), nameField.getText(), courseField.getText()));
            stage.close();
        });

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(idLabel, 0, 1);
        grid.add(idField, 1, 1);
        grid.add(courseLabel, 0, 2);
        grid.add(courseField, 1, 2);
        grid.add(addButton, 0, 3, 2, 1);

        Scene scene = new Scene(grid, 300, 200);
        stage.setScene(scene);
        stage.show();
    }

    private void showUpdateStudentForm() {
        Stage stage = new Stage();
        stage.setTitle("Update Student");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label idLabel = new Label("ID:");
        TextField idField = new TextField();

        Label courseLabel = new Label("Course:");
        TextField courseField = new TextField();

        ComboBox<Student> studentComboBox = new ComboBox<>();
        studentComboBox.setItems(students);
        studentComboBox.setPromptText("Select Student");

        studentComboBox.setOnAction(e -> {
            Student selectedStudent = studentComboBox.getSelectionModel().getSelectedItem();
            if (selectedStudent != null) {
                idField.setText(selectedStudent.getId());
                nameField.setText(selectedStudent.getName());
                courseField.setText(selectedStudent.getCourse());
            }
        });

        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> {
            Student selectedStudent = studentComboBox.getSelectionModel().getSelectedItem();
            if (selectedStudent != null) {
                selectedStudent.setName(nameField.getText());
                selectedStudent.setCourse(courseField.getText());
                stage.close();
            }
        });

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            Student selectedStudent = studentComboBox.getSelectionModel().getSelectedItem();
            if (selectedStudent != null) {
                students.remove(selectedStudent);
                stage.close();
            }
        });

        grid.add(studentComboBox, 0, 0, 2, 1);
        grid.add(nameLabel, 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(idLabel, 0, 2);
        grid.add(idField, 1, 2);
        grid.add(courseLabel, 0, 3);
        grid.add(courseField, 1, 3);
        grid.add(updateButton, 0, 4);
        grid.add(deleteButton, 1, 4);

        Scene scene = new Scene(grid, 300, 200);
        stage.setScene(scene);
        stage.show();
    }

    private void deleteStudent() {
        Student selectedStudent = table.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            students.remove(selectedStudent);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class Student {
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty course;

    public Student(String id, String name, String course) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.course = new SimpleStringProperty(course);
    }

    public String getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getCourse() {
        return course.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setCourse(String course) {
        this.course.set(course);
    }

    public StringProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty courseProperty() {
        return course;
    }
}
