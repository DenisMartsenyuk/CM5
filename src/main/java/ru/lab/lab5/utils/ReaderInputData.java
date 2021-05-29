package ru.lab.lab5.utils;

import ru.lab.lab5.entities.InputData;

import java.io.FileReader;
import java.util.Scanner;

public class ReaderInputData {
    Scanner scanner;
    InputData inputData;

    private final double EPS = 0.0001;

    public ReaderInputData() {
        inputData = new InputData();
    }

    public void read() {
        try {
            scanner = new Scanner(System.in);

            System.out.println("Каким образом будете вводить данные? Введите 'f' если из файла, 'k' - клавиатуры. ");
            String type = scanner.nextLine();
            if (!type.equals("f") && !type.equals("k")) {
                System.out.println("Выбран несуществующий тип ввода данных.");
                System.exit(0);
            }

            boolean isKeyboard = true;
            if (type.equals("f")) {
                isKeyboard = false;
                System.out.println("Введите путь до файла.");
                String path = scanner.nextLine();
                FileReader fileReader = new FileReader(path);
                scanner.close();
                scanner = new Scanner(fileReader);
            }

            if (isKeyboard) {
                System.out.println("Введите количество точек для поиска значения функции.");
            }
            int findNumber = scanner.nextInt();
            if (findNumber < 1) {
                System.out.println("Неверное количество точек для поиска.");
                System.exit(0);
            }
            readFindPoints(findNumber);

            if (isKeyboard) {
                System.out.println("Введите количество точек таблицы.");
            }
            int n = scanner.nextInt();
            if (n < 2) {
                System.out.println("Неверное количество точек таблицы.");
                System.exit(0);
            }

            if (isKeyboard) {
                System.out.println("Введите координаты точек через пробел. По точке на строку. (x y)\nВАЖНО! Точки надо вводить по возрастанию x.\nТакже должно выполняться равенство: x(i) - x(i + 1) = const.");
            }
            readPoints(n);


        } catch (Exception e) {
            System.out.println("Произошла ошибка при чтении данных.");
            System.exit(0);
        }
    }

    private Double readDouble() {
        String line = scanner.next();
        line = line.replace(',', '.');
        return Double.parseDouble(line);
    }

    private void readPoints(int n) {

        double firstX = readDouble();
        double firstY= readDouble();
        inputData.addTablePoint(firstX, firstY);

        double secondX = readDouble();
        double secondY = readDouble();
        checkSeriesX(firstX, secondX);
        inputData.addTablePoint(secondX, secondY);

        double previousX = secondX;
        double h = secondX - firstX;

        for (int i = 2; i < n; ++i) {
            double x = readDouble();
            checkSeriesXWithH(previousX, x, h);
            double y = readDouble();
            previousX = x;
            inputData.addTablePoint(x, y);
        }
    }

    private void checkSeriesX(double first, double second) {
        if (first > second) {
            System.out.println("Точки введены не по возрастанию.");
            System.exit(0);
        }
    }

    private void checkSeriesXWithH(double first, double second, double h) {
        checkSeriesX(first, second);
        if (Math.abs((second - first) - h)  > EPS) {
            System.out.println("Не выполняется равенство: x(i) - x(i + 1) = const.");
            System.exit(0);
        }
    }

    private void readFindPoints(int n) {
        for (int i = 0; i < n; ++i) {
            double x = readDouble();
            inputData.addFindPoint(x);
        }
    }

    public InputData getInputData() {
        return inputData;
    }
}
