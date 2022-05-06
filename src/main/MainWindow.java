package main;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MainWindow implements Initializable {

	@FXML
	private Canvas canvas;

	private GraphicsContext gc;
	private ArrayList<Double> ejeX;
	private ArrayList<Double> ejeY;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ejeX = new ArrayList<>();
		ejeY = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			ejeX.add(50.0 + 10.0 * i);
			ejeY.add(50 * Math.random());

		}

		double[] resultadoX = getMinMax(ejeX);
		double minX = resultadoX[0];
		double maxX = resultadoX[1];

		double[] resultadoY = getMinMax(ejeY);
		double minY = resultadoY[0];
		double maxY = resultadoY[1];
		System.out.println(minX + ", " + maxX + ", " + minY + ", " + maxY);

		double deltaPX = canvas.getWidth();
		double deltaDias = maxX - minX;
		double pendienteX = deltaPX / deltaDias;
		double interceptoX = pendienteX * minX * (-1);

		double deltaPY = -canvas.getHeight();
		double deltaAccidentes = maxY - minY;
		double pendienteY = deltaPY / deltaAccidentes;
		double interceptoY = pendienteY * maxY * (-1);
		
		
		
		double alfa = conversion(pendienteX, interceptoX, ejeX.get(5));
		System.out.println(pendienteX);
		

		gc = canvas.getGraphicsContext2D();

		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

		gc.setFill(Color.RED);
		for (int i = 0; i < ejeX.size(); i++) {
			gc.fillOval(
					conversion(pendienteX, interceptoX, ejeX.get(i)) - 3,
					conversion(pendienteY, interceptoY, ejeY.get(i)) - 3, 6, 6);
		}
		
		gc.setStroke(Color.RED);
		gc.setLineWidth(1.5);
		for (int i = 1; i < ejeX.size();i++) {
			gc.moveTo(
				conversion(pendienteX, interceptoX, ejeX.get(i-1)), 
				conversion(pendienteY, interceptoY, ejeY.get(i-1)));
		gc.lineTo(
				conversion(pendienteX, interceptoX, ejeX.get(i)), 
				conversion(pendienteY, interceptoY, ejeY.get(i)));
		gc.stroke();
		}
		
		
		
	}

	private double conversion(double m, double b, double input) {
		return m * input + b;
	}

	private double[] getMinMax(ArrayList<Double> arr) {
		ArrayList<Double> auxiliar = new ArrayList<>();
		auxiliar.addAll(arr);
		Collections.sort(auxiliar);
		double min = auxiliar.get(0);
		double max = auxiliar.get(auxiliar.size()-1);
		return new double[] { min, max };
	}

}