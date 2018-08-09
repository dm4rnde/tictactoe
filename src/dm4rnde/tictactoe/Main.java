/*
Copyright 2018 dm4rnde (dm4rnde@protonmail.com)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package dm4rnde.tictactoe;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * This is minimal and basic (runnable and functioning) realization of
 * tic-tac-toe game.
 * 
 * You can launch game from this class.
 * 
 * @author dm4rnde
 *
 */
public class Main extends Application {

	private Image imgCross = new Image(getClass().getResource("cross.png").toString());
	private Image imgCircle = new Image(getClass().getResource("circle.png").toString());

	private Button btnRestart;

	private BoxesLayer boxesLayer = new BoxesLayer();

	// game specific logic
	private boolean crossTurn = true;
	private boolean gameFinished;

	@Override
	public void start(Stage primaryStage) {

		// BorderPane root = new BorderPane();
		// StackPane root = new StackPane();

		Canvas canvas = new Canvas(Consts.frameImgW, Consts.frameImgH);
		canvas.setId("canvasArea");
		final GraphicsContext gc = canvas.getGraphicsContext2D();
		// root.getChildren().add(canvas);

		btnRestart = new Button();
		btnRestart.setText("Restart");
		btnRestart.setId("btnRestartId");
		btnRestart.setOnAction((ActionEvent event) -> restartGame(gc));

		HBox hBox = new HBox(5, btnRestart);
		hBox.setAlignment(Pos.CENTER);

		VBox root = new VBox(canvas, hBox);

		Scene scene = new Scene(root, Consts.wW, Consts.wH);
		scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);

		canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			if (!gameFinished) {
				btnRestart.setDisable(false);

				int x = new Double(e.getSceneX()).intValue();
				// int x = new Double(e.getSceneX()).intValue();
				// column position
				int boxRightPos = whichBoxInGivenMatrixSide(x, Consts.mX);
				int y = new Double(e.getSceneY()).intValue();
				// row position
				int boxDownPos = whichBoxInGivenMatrixSide(y, Consts.mY);

				// candidate box
				boolean onTheGridLines = isLocationOnAnyOfTheGridLine(root, x, y);
				// if on the grid, then don't allow to draw
				if (!onTheGridLines) {
					System.out.println(Consts.frameImgW);
					System.out.println(Consts.frameImgH);
					Box box = new Box(boxRightPos, boxDownPos, Consts.frameImgW, Consts.frameImgH, Consts.imgW,
							Consts.imgH);
					if (!boxesLayer.isAlreadyFilled(box)) {
						if (crossTurn) {
							box.draw(new Integer(boxDownPos).doubleValue(), new Integer(y).doubleValue(), gc, imgCross,
									true);
						} else {
							box.draw(boxDownPos, new Double(y), gc, imgCircle, false);
						}
						boxesLayer.addBox(box);
						if (PatternMatcher.checkForMatch(crossTurn, boxesLayer.getBoxes())) {
							gameWonMessage(gc, canvas, crossTurn);
						}
						crossTurn = crossTurn ? false : true;
					} else {
						System.out.println("no drawing; this section already drawn");
					}
				} else {
					System.out.println("no drawing; mouse on grid line");
				}
			}
		});

	}

	private void gameWonMessage(GraphicsContext gc, Canvas canvas, boolean crossTurn) {

		String cr = crossTurn ? "crosses" : "circles";
		// Text text = new Text(cr + " won");
		// text.setFill(Color.web("fabbff"));
		gc.setFill(Color.rgb(255, 255, 255, 0.7));
		gc.fillRoundRect(20, 80, 170, 50, 15, 15);
		if (crossTurn) {
			gc.setStroke(Color.rgb(0, 200, 0, 0.5));
		} else {
			gc.setStroke(Color.rgb(0, 0, 200, 0.5));
		}
		gc.strokeRoundRect(20, 80, 170, 50, 15, 15);

		if (crossTurn) {
			gc.setStroke(Color.GREEN);
		} else {
			gc.setStroke(Color.BLUE);
		}
		// gc.setFontSmoothingType(FontSmoothingType.LCD);
		gc.setFont(Font.font("Serif", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 30));

		long w = Math.round(canvas.getWidth() / 2);
		long h = Math.round(canvas.getHeight() / 2);

		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		gc.strokeText(cr + " won", w, h);

		gameFinished = true;

	}

	private void restartGame(GraphicsContext gc) {

		gameFinished = false;
		gc.clearRect(0, 0, Consts.frameImgW, Consts.frameImgH);
		boxesLayer.removeBoxes();
		btnRestart.setDisable(true);

	}

	/**
	 * Having a pixel coordinates of the mouse cursor, tell if color that is on the
	 * background image (grid), behind that pixel, matches with the color of grid
	 * lines. If pixel color matches with grid color, then coordinates are on the
	 * grid.
	 * 
	 * @param root
	 * @param x
	 *            coordinate x of mouse cursor
	 * @param y
	 *            coordinate y of mouse cursor
	 * @return true if coordinates are on the grid, false if not
	 */
	private boolean isLocationOnAnyOfTheGridLine(Pane root, int x, int y) {

		boolean onTheGridLine = true;
		Image image = root.getBackground().getImages().get(0).getImage();
		PixelReader r = image.getPixelReader();
		Color argb = r.getColor(x, y);
		// expect grid to be green and other area to be white !
		if (argb.getRed() == 0 && argb.getGreen() == 0 && argb.getBlue() == 0) {
			onTheGridLine = false;
		}
		return onTheGridLine;

	}

	/**
	 * @param givenVal
	 *            x or y coord (on the scene) of the mouse pointer
	 * @param matrixSides
	 *            either sides left to right, or sides top to bottom; list of
	 *            lengths; if given x, then lengths represent lengths of each
	 *            box's/col's right side from the left; if y, then represent lengths
	 *            of each box's/row's bottom side from the top
	 * @return corresponding position of given value in provided matrix; -1 in case
	 *         of problem
	 */
	private int whichBoxInGivenMatrixSide(int givenVal, int[] matrixSides) {
		return Utils.findFirstPosOfNumInNumArrThatIsLargerThanGivenNum(givenVal, matrixSides);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
