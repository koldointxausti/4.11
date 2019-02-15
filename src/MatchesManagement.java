import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MatchesManagement {
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Enter the file name of the file you want to read.");
			System.out.println("example: java Matches ../Champions.txt");
		}
		try {
			File teamInfo = new File("../TeamInfo.txt");
			ArrayList<Team> teams = new ArrayList<Team>();
			Scanner sc1 = new Scanner(teamInfo);
			while (sc1.hasNextLine()) {
				Team a = new Team();
				String tokens[] = sc1.nextLine().split("::");
				a.setName(tokens[0]);
				a.setNationality(tokens[1]);
				a.setStadium(tokens[2]);
				a.setCapacity(Integer.parseInt(tokens[3]));
				teams.add(a);
			}

			ArrayList<FootballMatch> list = new ArrayList<FootballMatch>();

			System.out.println(args[0]);
			File matches = new File(args[0]);
			try {
				Scanner sc = new Scanner(matches);
				while (sc.hasNextLine()) {
					FootballMatch match = new FootballMatch();
					String tokens[] = sc.nextLine().split("::");
					for (int x = 0; x < teams.size(); x++) {
						if (tokens[0].equals(teams.get(x).getName())) {
							match.setLocalTeam(teams.get(x));
							break;
						}
					}
					for (int x = 0; x < teams.size(); x++) {
						if (tokens[1].equals(teams.get(x).getName())) {
							match.setVisitorTeam(teams.get(x));
							break;
						}
					}
					match.setGoalsLocal(Integer.parseInt(tokens[2]));
					match.setGoalsVisitor(Integer.parseInt(tokens[3]));
					list.add(match);
				}
				for (int x = 0; x < list.size(); x++) {
					System.out.println("\n*MATCH RESULT*");
					System.out.println(list.get(x).getLocalTeam().getName()+"\t"+list.get(x).getGoalsLocal()+ " - "+list.get(x).getGoalsVisitor()+"\t"+ list.get(x).getVisitorTeam().getName()+"\n");

					System.out.println("*"+list.get(x).getLocalTeam().getName().toUpperCase()+"*");
					System.out.println("Nationality: " + list.get(x).getLocalTeam().getNationality());
					System.out.println("Stadium: " + list.get(x).getLocalTeam().getStadium());
					System.out.println("Capacity: " + list.get(x).getLocalTeam().getCapacity());
					
				}
				sc.close();
			} catch (FileNotFoundException e) {
				System.out.println("Enter the name of the file you want to read.");
				System.out.println("example: java MatchArrayList ../Champions.txt ../CopaDelRey.txt");
			}

			System.out.println();
			sc1.close();
		} catch (FileNotFoundException e) {
			System.out.println();
		}

	}
}
