package Game.Minigames.Bewerbungstest;

public class Frage
{
   private String frage;
   private String awmA;
   private String awmB;
   private String awmC;
   private String awmD;
   private int antwort;
   
   public Frage(String frage, String awmA, String awmB, String awmC, String awmD, int antwort)
   {
      this.frage = frage;
      this.awmA= awmA;
      this.awmB = awmB;
      this.awmC = awmC;
      this.awmD = awmD;
      this.antwort = antwort;

   }
   public String gibFrage()
   {
      return frage;
   }
   
   public String gibA()
   {
      return awmA;
   }
   
   public String gibB()
   {
      return awmB;
   }
   
   public String gibC()
   {
      return awmC;
   }
   
   public String gibD()
   {
      return awmD;
   }
   
   public int gibAntwort()
   {

      return antwort;
   }
}