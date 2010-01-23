package Game.Minigames.Sudoku;

import java.util.Random;

/**
 * Write a description of class FensterAbschicken here.
 *
 * @version 22.01.2010
 */
public class Sudokuspeicher
{
   private String[][] startbelegung1, startbelegung2, startbelegung3;
   private String belegreihe1, belegreihe2, belegreihe3, belegreihe4;
   private Random zufall;
   private static String x;
   private int r;

   public Sudokuspeicher()
   {
      startbelegung1 = new String [9][9];
      startbelegung2 = new String [9][9];
      startbelegung3 = new String [9][9];
      
      zufall = new Random();
      r = zufall.nextInt(2);

      belegreihe1 = "279685413835741692614329857542863971396174285781592346953218764168437529427956138"; //leicht 0000
      belegreihe2 = "314627598928315467675948321193762854452893176867154239746231985281579643539486712"; //leicht 0001
      belegreihe3 = "431962758762518943958734621546291837329876514187345296215683479694127385873459162"; //mittel 0081
      belegreihe4 = "912846573683571294457329168829613457164957832375284619746192385591438726238765941"; //Internetvorlage

   }
   public void startenLeicht() //belegt das Sudoku bei Schwierigkeitsgrad: Leicht
   {
     if(r == 0)
     {
        x = belegreihe1;
        
        for(int zeile = 0; zeile < 9; zeile++)
        {
          for(int spalte = 0; spalte < 9; spalte++)
          {
            startbelegung1[zeile][spalte] = x.substring((zeile*9 + spalte), (zeile*9 + spalte)+1);
          }
        }
        startbelegung1[0][1] = "";
        startbelegung1[0][2] = "";
        startbelegung1[0][7] = "";
        startbelegung1[1][0] = "";
        startbelegung1[1][3] = "";
        startbelegung1[1][8] = "";
        startbelegung1[2][0] = "";
        startbelegung1[2][4] = "";
        startbelegung1[2][8] = "";
        startbelegung1[3][1] = "";
        startbelegung1[3][4] = "";
        startbelegung1[3][7] = "";
        startbelegung1[4][2] = "";
        startbelegung1[4][5] = "";
        startbelegung1[4][6] = "";
        startbelegung1[5][1] = "";
        startbelegung1[5][4] = "";
        startbelegung1[5][7] = "";
        startbelegung1[6][0] = "";
        startbelegung1[6][4] = "";
        startbelegung1[6][8] = "";
        startbelegung1[7][0] = "";
        startbelegung1[7][5] = "";
        startbelegung1[7][8] = "";
        startbelegung1[8][1] = "";
        startbelegung1[8][2] = "";
        startbelegung1[8][6] = "";
        startbelegung1[8][7] = "";
     }
     else
     {
        x = belegreihe2;
        
        for(int zeile = 0; zeile < 9; zeile++)
        {
          for(int spalte = 0; spalte < 9; spalte++)
          {
            startbelegung1[zeile][spalte] = x.substring((zeile*9 + spalte), (zeile*9 + spalte)+1);
          }
        }
        startbelegung1[0][1] = "";
        startbelegung1[0][2] = "";
        startbelegung1[0][7] = "";
        startbelegung1[1][0] = "";
        startbelegung1[1][3] = "";
        startbelegung1[1][8] = "";
        startbelegung1[2][0] = "";
        startbelegung1[2][4] = "";
        startbelegung1[2][8] = "";
        startbelegung1[3][1] = "";
        startbelegung1[3][4] = "";
        startbelegung1[3][7] = "";
        startbelegung1[4][2] = "";
        startbelegung1[4][5] = "";
        startbelegung1[4][6] = "";
        startbelegung1[5][1] = "";
        startbelegung1[5][3] = "";
        startbelegung1[5][4] = "";
        startbelegung1[5][8] = "";
        startbelegung1[2][2] = "";
        startbelegung1[3][5] = "";
        startbelegung1[6][0] = "";
        startbelegung1[6][4] = "";
        startbelegung1[6][8] = "";
        startbelegung1[7][0] = "";
        startbelegung1[7][5] = "";
        startbelegung1[7][8] = "";
        startbelegung1[8][1] = "";
        startbelegung1[8][2] = "";
        startbelegung1[8][6] = "";
        startbelegung1[8][7] = "";
     }
   }
   public void startenMittel() //belegt das Sudoku bei Schwierigkeitsgrad: Mittel
   {
     if(r == 0)
     {
       x = belegreihe3;
     
       for(int zeile = 0; zeile < 9; zeile++)
       {
         for(int spalte = 0; spalte < 9; spalte++)
         {
           startbelegung2[zeile][spalte] = x.substring((zeile*9 + spalte), (zeile*9 + spalte)+1);
         }
       }
     
        startbelegung2[0][1] = "";
        startbelegung2[0][2] = "";
        startbelegung2[0][7] = "";
        startbelegung2[1][0] = "";
        startbelegung2[1][3] = "";
        startbelegung2[1][8] = "";
        startbelegung2[2][0] = "";
        startbelegung2[2][4] = "";
        startbelegung2[2][8] = "";
        startbelegung2[3][1] = "";
        startbelegung2[3][4] = "";
        startbelegung2[3][7] = "";
        startbelegung2[4][2] = "";
        startbelegung2[4][5] = "";
        startbelegung2[4][6] = "";
        startbelegung2[5][1] = "";
        startbelegung2[5][4] = "";
        startbelegung2[5][7] = "";
        startbelegung2[6][0] = "";
        startbelegung2[6][4] = "";
        startbelegung2[6][8] = "";
        startbelegung2[7][0] = "";
        startbelegung2[7][5] = "";
        startbelegung2[7][8] = "";
        startbelegung2[8][1] = "";
        startbelegung2[8][2] = "";
        startbelegung2[8][6] = "";
        startbelegung2[8][7] = "";
     }
     else
     {
        x = belegreihe4;
        for(int zeile = 0; zeile < 9; zeile++)
        {
          for(int spalte = 0; spalte < 9; spalte++)
          {
            startbelegung2[zeile][spalte] = x.substring((zeile*9 + spalte), (zeile*9 + spalte)+1);
          }
        }
        startbelegung2[0][1] = "";
        startbelegung2[0][2] = "";
        startbelegung2[0][7] = "";
        startbelegung2[1][0] = "";
        startbelegung2[1][3] = "";
        startbelegung2[1][8] = "";
        startbelegung2[2][0] = "";
        startbelegung2[2][4] = "";
        startbelegung2[2][8] = "";
        startbelegung2[3][1] = "";
        startbelegung2[3][4] = "";
        startbelegung2[3][7] = "";
        startbelegung2[4][2] = "";
        startbelegung2[4][5] = "";
        startbelegung2[4][6] = "";
        startbelegung2[5][1] = "";
        startbelegung2[5][4] = "";
        startbelegung2[5][7] = "";
        startbelegung2[6][0] = "";
        startbelegung2[6][4] = "";
        startbelegung2[6][8] = "";
        startbelegung2[7][0] = "";
        startbelegung2[7][5] = "";
        startbelegung2[7][8] = "";
        startbelegung2[8][1] = "";
        startbelegung2[8][2] = "";
        startbelegung2[8][6] = "";
        startbelegung2[8][7] = "";
     }
   }
   public String getStartLeicht(int z, int s)
   {
     return startbelegung1[z][s];
   }
   public String getStartMittel(int z, int s)
   {
     return startbelegung2[z][s];
   }
   public static String getReihe()
   {
     return x;
   }
}