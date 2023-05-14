import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import builders.StudentsBuilder;
import entities.Studant;

/**
 * 
 * @author Rodrigo Mora Azocar
 *
 */

public class Main {

	public static void main(String[] args) {
		var allStudents = StudentsBuilder.getAllStudents();

		// Agora vamos as atividades
		/*
		 * 
		 * 1. Recupere da lista os alunos que passaram de ano (nota minima 7.0). - Exiba
		 * os dados nesse formato: <código> - <nome> : Média = <nota>
		 */

		System.out.println("==== ALUNOS APROVADOS - NOTAS COM NOTAS DESDE 7.0 E NOTAS SUPERIORES ======");
		for (Studant studant : allStudents) { // inicio for

			float average = (studant.getTestOne() + studant.getTestTwo() + studant.getTestThree()) / 3;
			if (average >= 7.0) { // inicio if
				System.out.println(
						"Código:" + studant.getCode() + " - " + "Nome:" + studant.getName() + " : Média = " + average);
			} // fim if
		} // fim for

		/**
		 * 2. Recupere da lista os alunos que não passaram de ano. - Exiba os dados
		 * nesse formato: <código> - <nome> : Média = <media> (Faltou = <nota_faltante>)
		 */

		System.out.println("\nALUNOS REPROVADOS - NOTAS INFERIORES A 7.0 - QUE NOTA FALTOU PARA APROVAR");
		for (Studant student : allStudents) {
			float average = (student.getTestOne() + student.getTestTwo() + student.getTestThree()) / 3;

			if (average < 7.0f) {
				System.out.printf("Código: %d - Nome: %s : Média = %.1f (Faltou = %.1f)%n", student.getCode(),
						student.getName(), average, 7.0f - average);
			}
		}
		/**
		 * 3. Traga os alunos que tiraram a nota máxima (nota 10). - Exiba os dados
		 * nesse formato: <código> - <nome>
		 */

		System.out.println("\n  === ALUNOS COM NOTA MÁXIMA (NOTA 10) EM ALGUNS DOS TESTES ");

		for (Studant studant : allStudents) {

			if (studant.getTestOne() == 10.0f || studant.getTestTwo() == 10.0f || studant.getTestThree() == 10.0f)
				System.out.println("Código:" + studant.getCode() + "-" + "Nome:" + studant.getName());

		}

		/**
		 * 4. Traga o aluno que tirou a menor nota, em caso de notas iguais, traga ambos
		 * os alunos. - Exiba os dados nesse formato: <código> - <nome> : Nota = <nota>
		 * 
		 */

		System.out.println("\n==== ALUNOS COM A MENOR NOTA ====");

		float lowestNote = Float.MAX_VALUE;
		List<Studant> studentsWithLowestNote = new ArrayList<>();

		for (Studant student : allStudents) {
			float[] notes = { student.getTestOne(), student.getTestTwo(), student.getTestThree() };

			for (float note : notes) {
				if (note < lowestNote) {
					lowestNote = note;
					studentsWithLowestNote.clear();
					studentsWithLowestNote.add(student);
				} else if (note == lowestNote) {
					if (!studentsWithLowestNote.contains(student)) {
						studentsWithLowestNote.add(student);
					}
				}
			}
		}

		for (Studant studentWithLowestNote : studentsWithLowestNote) {
			System.out.println("Código: " + studentWithLowestNote.getCode() + " - Nome: "
					+ studentWithLowestNote.getName() + " : Nota = " + lowestNote);
		}

		/**
		 * 5. Faça uma lista com top 3 notas de alunos. Em caso de notas iguais coloque
		 * todos na mesma posição. - Ex: 1º - Fulano : Nota = 10.0; - Beltrano : Nota =
		 * 10.0; 2º - Joãozinho : Nota = 9.0; 3º - Mariazinha : Nota = 8.9; - Exiba os
		 * dados nesse formato: <posicao> - <nome> : Nota = <nota>
		 * 
		 */

		System.out.println("\n==== TOP 3 NOTAS DE ALUNOS ====");

		// Criamos um mapa para guardar a maior nota de cada aluno
		Map<Studant, Float> bestGrades = new HashMap<>();

		// Preenchemos o mapa com a maior nota de cada aluno
		for (Studant studant : allStudents) {
			float bestGrade = Math.max(Math.max(studant.getTestOne(), studant.getTestTwo()), studant.getTestThree());
			bestGrades.put(studant, bestGrade);
		}

		// Ordenamos as entradas do mapa em ordem decrescente de notas
		List<Map.Entry<Studant, Float>> sortedEntries = new ArrayList<>(bestGrades.entrySet());
		sortedEntries.sort((a, b) -> Float.compare(b.getValue(), a.getValue()));

		// Mostramos as 3 notas mais altas
		float previousGrade = -1.0f;
		int rank = 0;

		for (Map.Entry<Studant, Float> entry : sortedEntries) {
			if (entry.getValue() != previousGrade) {
				rank++;
				previousGrade = entry.getValue();
			}

			if (rank > 3) {
				break;
			}

			System.out.printf("%dº - %s : Nota = %.1f%n", rank, entry.getKey().getName(), entry.getValue());
		}

		// Se existirem menos de 3 notas distintas, mostramos um aviso
		if (rank < 3) {
			System.out.println("Atenção: existem menos de 3 notas distintas entre os alunos.");
		}

		/**
		 * 6. Faça uma lista com as 3 menores notas de alunos. Em caso de notas iguais
		 * coloque todos na mesma posição. Exemplo igual a anterior - Exiba os dados
		 * nesse formato: <posicao> - <nome> : Nota = <nota>
		 */

		System.out.println("\n==== 3 MENORES NOTAS DE ALUNOS ====");

		// Criamos um mapa para guardar a menor nota de cada aluno
		Map<Studant, Float> worstGrades = new HashMap<>();

		// Preenchemos o mapa com a menor nota de cada aluno
		for (Studant studant : allStudents) {
			float worstGrade = Math.min(Math.min(studant.getTestOne(), studant.getTestTwo()), studant.getTestThree());
			worstGrades.put(studant, worstGrade);
		}

		// Ordenamos as entradas do mapa em ordem crescente de notas
		List<Map.Entry<Studant, Float>> sortedEntriesLowest = new ArrayList<>(worstGrades.entrySet());
		sortedEntriesLowest.sort((a, b) -> Float.compare(a.getValue(), b.getValue()));

		// Mostramos as 3 notas mais baixas
		float previousLowestGrade = -1.0f;
		int rankLowest = 0;

		for (Map.Entry<Studant, Float> entry : sortedEntriesLowest) {
			if (entry.getValue() != previousLowestGrade) {
				rankLowest++;
				previousLowestGrade = entry.getValue();
			}

			if (rankLowest > 3) {
				break;
			}

			System.out.printf("%dº - %s : Nota = %.1f%n", rankLowest, entry.getKey().getName(), entry.getValue());
		}

		// Se existirem menos de 3 notas distintas, mostramos um aviso
		if (rankLowest < 3) {
			System.out.println("Atenção: existem menos de 3 notas distintas entre os alunos.");
		}
		
		/**
		 *  7. Monte a média de todos os alunos e exiba em tela ordenando da maior para a menor nota.
            - Exiba os dados nesse formato: <posicao> - <código> - <nome> : Média = <nota>
		 */
		
		System.out.println("\n==== MÉDIA DE TODOS OS ALUNOS ORDENADA DA MAIOR PARA A MENOR ====");

		// Criamos um mapa para guardar a média de cada aluno
		Map<Studant, Float> averages = new HashMap<>();

		// Preenchemos o mapa com a média de cada aluno
		for (Studant studant : allStudents) {
		    float average = (studant.getTestOne() + studant.getTestTwo() + studant.getTestThree()) / 3;
		    averages.put(studant, average);
		}

		// Ordenamos as entradas do mapa em ordem decrescente de média
		List<Map.Entry<Studant, Float>> sortedAverages = new ArrayList<>(averages.entrySet());
		sortedAverages.sort((a, b) -> Float.compare(b.getValue(), a.getValue()));

		// Mostramos as médias em ordem decrescente
		int position = 0;

		for (Map.Entry<Studant, Float> entry : sortedAverages) {
		    position++;
		    System.out.printf("%dº - %d - %s : Média = %.1f%n", position, entry.getKey().getCode(), entry.getKey().getName(), entry.getValue());
		}


	} 
} 
