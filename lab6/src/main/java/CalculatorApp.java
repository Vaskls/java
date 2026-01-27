import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CalculatorApp extends Application {

    private TextField display = new TextField("0");
    private Label historyDisplay = new Label("");
    private double fnum = 0;
    private String operator = "";
    private boolean start = true;

    @Override
    public void start(Stage primaryStage) {
        // --- UI Setup (Same as your original code) ---
        historyDisplay.setAlignment(Pos.CENTER_RIGHT);
        historyDisplay.setMinHeight(30);
        historyDisplay.setMaxWidth(Double.MAX_VALUE);
        historyDisplay.setStyle("-fx-text-fill: #666666; -fx-padding: 0 10 0 0;");
        historyDisplay.setFont(Font.font("Segoe UI", 14));

        display.setEditable(false);
        display.setAlignment(Pos.CENTER_RIGHT);
        display.setMinHeight(70);
        display.setStyle("-fx-background-color: transparent; -fx-border-color: none; -fx-text-fill: #000;");
        display.setFont(Font.font("Segoe UI Semibold", 36));

        GridPane grid = new GridPane();
        grid.setHgap(2);
        grid.setVgap(2);
        grid.setPadding(new Insets(2));

        String[] labels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        int row = 0; int col = 0;
        for (String label : labels) {
            Button btn = createButton(label);
            grid.add(btn, col, row);
            col++;
            if (col > 3) { col = 0; row++; }
        }

        VBox displayArea = new VBox(historyDisplay, display);
        displayArea.setPadding(new Insets(10, 0, 10, 0));
        
        VBox mainLayout = new VBox(displayArea, grid);
        mainLayout.setStyle("-fx-background-color: #e6e6e6;");

        StackPane root = new StackPane();
        
        root.getChildren().add(mainLayout);

        Pane overlay = new Pane(); 
        overlay.setPickOnBounds(false);

        Button floatingBtn = new Button("T");
        floatingBtn.setStyle("-fx-background-color: rgba(255, 0, 0, 0.7); -fx-text-fill: white; -fx-font-weight: bold;");
        
        floatingBtn.setLayoutX(55);  
        floatingBtn.setLayoutY(235); 
        floatingBtn.setMinSize(40, 40);

        overlay.getChildren().add(floatingBtn);
        root.getChildren().add(overlay); // Added last = highest Z-index

        Scene scene = new Scene(root, 320, 380);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private Button createButton(String text) {
        Button btn = new Button(text);
        btn.setMinSize(78, 60);
        btn.setFont(Font.font("Segoe UI", 18));
        
        if (Character.isDigit(text.charAt(0))) {
            btn.setStyle("-fx-background-color: #ffffff; -fx-border-color: #dcdcdc;");
        } else if (text.equals("=")) {
            btn.setStyle("-fx-background-color: #0078d7; -fx-text-fill: white;");
        } else {
            btn.setStyle("-fx-background-color: #f3f3f3; -fx-border-color: #dcdcdc;");
        }

        btn.setOnAction(e -> processEvent(text));
        return btn;
    }

    private void processEvent(String value) {
        display.setFont(Font.font("Segoe UI Semibold", 36));
        if (Character.isDigit(value.charAt(0))) {
            if (start) {
                display.setText(value);
                start = false;
            } else {
                display.setText(display.getText() + value);
            }
        } else if (value.equals("C")) {
            display.setText("0");
            historyDisplay.setText("");
            fnum = 0;
            operator = "";
            start = true;
        } else if (value.equals("=")) {
            if (operator.isEmpty()) return;
            double snum = Double.parseDouble(display.getText());
            historyDisplay.setText(formatNumber(fnum) + " " + operator + " " + formatNumber(snum) + " =");
            calculate(snum);
            operator = "";
            start = true;
        } else {
            fnum = Double.parseDouble(display.getText());
            operator = value;
            historyDisplay.setText(formatNumber(fnum) + " " + operator);
            start = true;
        }
    }

    private void calculate(double snum) {
        try {
            switch (operator) {
                case "+" -> fnum += snum;
                case "-" -> fnum -= snum;
                case "*" -> fnum *= snum;
                case "/" -> {
                    if (snum == 0) throw new ArithmeticException();
                    fnum /= snum;
                }
            }
            display.setText(formatNumber(fnum));
        } catch (ArithmeticException e) {
            display.setFont(Font.font("Segoe UI Semibold", 16));
            display.setText("Cannot divide by zero");
            historyDisplay.setText("");
            fnum = 0;
            operator = "";
        }
    }

    private String formatNumber(double d) {
        if (d == (long) d) return String.format("%d", (long) d);
        return String.valueOf(d);
    }

    public static void main(String[] args) {
        launch(args);
    }
}