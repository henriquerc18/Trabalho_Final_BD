package Pacote;
/*
 * Escore Padrao: Z = 500 + ( (NOTA - M) / D ), sendo:
 * D = âˆš [Î£ (Notai â€“ M)^2 ]  o desvio padrao da prova
 * M = media das provas
 * Notai = nota do iesimo aluno na prova. 
 */

public class EscorePadrao {
	private float Escore,M;
	float[] n = new float[3];
	public void EscorePad(float n1, float n2, float n3 ){
		n[0]=n1;
		n[1]=n2;
		n[2]=n3;
		for(int i=0;i<3;i++) M=M+n[i];
		M=M/3;
		for(int i=0;i<3;i++) Escore=(float) Math.sqrt(n[i]-M);
		}
	public float getEscore(){
		return Escore;
	}
	}
