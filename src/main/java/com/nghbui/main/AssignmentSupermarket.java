package com.nghbui.main;

import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import com.nghbui.model.ShortShift;
import com.nghbui.model.Work;

import java.util.List;

public class AssignmentSupermarket {


    public static void main(String[] args) {
        Loader.loadNativeLibraries();

        ReadWrite readWrite = new ReadWrite();
        System.out.println("Hello");
        readWrite.ReadData();

        for (ShortShift shortShift : ReadWrite.shortShifts) {
            if (shortShift.getShortShiftID() > 0) {
                Assignment(shortShift, shortShift.getWorks());
            }
        }
        readWrite.WriteData(ReadWrite.shortShifts);

    }
    public static void Assignment(ShortShift shortShift, List<Work> works) {
        MPSolver solver = MPSolver.createSolver("GLOP"); //SCIP and GLOP

        int numWork = works.size();
        int numHeadCount = shortShift.getHeadCount();

        MPVariable x[][] = new MPVariable[numHeadCount][numWork];
        float minutesFinishWork[][] = new float[numHeadCount][numWork];

        for (int i=0;i<numHeadCount;i++) {
            for (int j=0; j<numWork;j++) {

                int numWorking = works.get(j).getQuantityHumansWorking();
                if (numWorking == 100) {
                    numWorking = numHeadCount; //Const
                }
                minutesFinishWork[i][j] = works.get(j).getMinutesFinishWork()/(float)numWorking;
                x[i][j] = solver.makeIntVar(0.0,1.0,"");
            }
        }

        //Constraint
        //Number Humans Working
        for (int i=0;i<numWork;i++) {
            int quantityHumansWorking =works.get(i).getQuantityHumansWorking();
            MPConstraint c;
            if (quantityHumansWorking==100) {
                c = solver.makeConstraint(numHeadCount,numHeadCount,"");
            } else {
                c = solver.makeConstraint(quantityHumansWorking,quantityHumansWorking,"");
            }

            for (int j = 0; j<numHeadCount;j++) {
                c.setCoefficient(x[j][i],1);
            }
        }

        int shortShiftTime = shortShift.getShortShiftTime();

        //Effort < Shift time
        for (int i=0;i<numHeadCount;i++) {
            MPConstraint c = solver.makeConstraint(0,shortShiftTime,"");
            for (int j=0;j<numWork;j++) {
                c.setCoefficient(x[i][j],minutesFinishWork[i][j]);
            }
        }

        MPSolver.ResultStatus resultStatus= solver.solve();

        if (resultStatus == MPSolver.ResultStatus.OPTIMAL || resultStatus == MPSolver.ResultStatus.FEASIBLE) {
            for (int i = 0; i < numHeadCount; i++) {
                for (int j = 0; j < numWork; j++) {
                    if (x[i][j].solutionValue() > 0.5) {
                        System.out.println("Worker " + i + " assigned to task " + j);
                        works.get(j).getWorkers().add(i+1);// Worker i assigned to task j
                    }
                }
            }
            shortShift.setWorks(works);
        } else {
            System.out.println("Date"+shortShift.getDate().toString()+ "-- Short Shift ID: "+shortShift.getShortShiftID());
            System.out.println("No solution found.");
            System.out.println(resultStatus.toString());
        }
        System.out.println("--------------------------------------------------------");

    }

}
