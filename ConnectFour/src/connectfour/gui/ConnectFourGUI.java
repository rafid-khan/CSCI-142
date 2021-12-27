package connectfour.gui;

import connectfour.model.ConnectFourBoard;
import connectfour.model.Observer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.*;


/**
 * A JavaFX GUI for the Connect Four game.
 *
 * @author RIT CS
 * @author Rafid Khan
 */
public class ConnectFourGUI extends Application implements Observer<ConnectFourBoard> {
    /** variable holding the empty image */
    private final Image empty = new Image(Objects.requireNonNull(getClass().
            getResourceAsStream("empty.png")));

    /** variable holding the black disc image for player 1 */
    private final Image playerOne = new Image(Objects.requireNonNull(getClass().
            getResourceAsStream("p1black.png")));

    /** variable holding the red disc image for player 2 */
    private final Image playerTwo = new Image(Objects.requireNonNull(getClass().
            getResourceAsStream("p2red.png")));

    /** what column the move was made in */
    private int move;

    /** whether the game has ended or not */
    private boolean end;

    /** a button object that represents every position in the connect four board */
    private helperButton[][] buttonsArray;

    /** variable which represents the board */
    private ConnectFourBoard game;

    /** label object that stores the number of moves */
    private Label moves;

    /** label object that stores the player's turn */
    private Label playerTurn;

    /** label object that stores the game status  */
    private Label gameStatus;

    @Override
    public void init() {
        this.game = new ConnectFourBoard();
        game.addObserver(this);

        this.move = 0;
        this.end = false;
        this.buttonsArray = new helperButton
                [ConnectFourBoard.ROWS][ConnectFourBoard.COLS];
        this.moves = new Label(this.game.getMovesMade() + " moves made");
        this.playerTurn = new Label("Current player: " + this.game.getCurrentPlayer());
        this.gameStatus = new Label("Status: " + this.game.getGameStatus());
    }

    /**
     * start()
     * Construct the layout for the game.
     *
     * @param stage container (window) in which to render the GUI
     * @throws Exception if there is a problem
     */
    public void start(Stage stage) throws Exception {
        BorderPane borderPane = new BorderPane();
        HBox information = new HBox();
        init();

        information.getChildren().add(this.moves);
        information.getChildren().add(this.playerTurn);
        information.getChildren().add(this.gameStatus);
        information.setAlignment(Pos.CENTER);
        information.setPadding(new Insets(50, 10 , 10, 10));
        information.setSpacing(125);
        borderPane.setBottom(information);

        GridPane gridPane = new GridPane();
        int row = 0;
        while (true) {
            if (row >= ConnectFourBoard.ROWS) break;
            int column = 0;
            do {
                helperButton button = new helperButton(column);
                button.setOnAction(event -> {
                    if (!this.game.isValidMove(button.column)) return;
                    this.move = button.column;
                    this.game.makeMove(button.column);
                });
                gridPane.add(button, column, row);
                this.buttonsArray[row][column] = button;
                column++;
            } while (column < ConnectFourBoard.COLS);
            row++;
        }
        borderPane.setCenter(gridPane);

        Scene scene = new Scene(borderPane);
        stage.setTitle("Connect Four GUI");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * update()
     * Called by the model, model.ConnectFourBoard, whenever there is a state change
     * that needs to be updated by the GUI.
     *
     * @param connectFourBoard the board
     */
    @Override
    public void update(ConnectFourBoard connectFourBoard) {
        if (!this.end) {
            ConnectFourBoard.Player turn;
            int row = 0;
            while (row < ConnectFourBoard.ROWS) {
                turn = this.game.getContents(row, this.move);
                switch (turn) {
                    case P1 -> this.buttonsArray[row][this.move].
                            setGraphic(new ImageView(playerOne));
                    case P2 -> this.buttonsArray[row][this.move].
                            setGraphic(new ImageView(playerTwo));
                    default -> this.buttonsArray[row][this.move].
                            setGraphic(new ImageView(empty));
                }
                row++;
            }
            this.moves.setText(this.game.getMovesMade() + " moves made ");
            this.playerTurn.setText("Current player: " + this.game.getCurrentPlayer());
            this.gameStatus.setText("Status: " + this.game.getGameStatus());

            if (this.game.getGameStatus() != ConnectFourBoard.Status.NOT_OVER) {
                this.end = true;
            }
        }
    }

    /**
     * main()
     * The main method expects the host and port.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }


    //                                *** HELPER METHODS / OBJECTS ***

    /**
     * this is a helper object that creates a button object that
     * can hold the column in the format of helperButton button[][]
     * by storing the column no. we can recall this value to ensure the
     * functionality for logic of the game
     */
    private class helperButton extends Button {
        private final int column;
        public helperButton(int column) {
            this.column = column;
            this.setGraphic(new ImageView(empty));
        }
    }

}

