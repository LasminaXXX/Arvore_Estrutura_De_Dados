package Java;
import java.util.Stack;
import java.util.Scanner;



class Main{
    public static void main(String[] args) {
        Arvore_Binaria arvore = new Arvore_Binaria();

        arvore.inserir_no(6);
        arvore.inserir_no(2);
        arvore.inserir_no(1);
        arvore.inserir_no(4);
        arvore.inserir_no(3);
        arvore.inserir_no(8);

        Scanner scanner = new Scanner(System.in);
        int escolha = -1;
        while(escolha !=0) {
            System.out.println("\nMenu:");
            System.out.println("1. Inserir nó");
            System.out.println("2. Visualizar em ordem");
            System.out.println("3. Menor valor");
            System.out.println("4. Maior valor");
            System.out.println("5. Mostrar folhas");
            System.out.println("6. Mostrar ancestrais");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    System.out.print("Digite o valor a ser inserido: ");
                    int valor = scanner.nextInt();
                    arvore.inserir_no(valor);
                    break;
                case 2:
                    System.out.println("Visualização em ordem:");
                    String repres = arvore.Mostrar_Arvore();
                    for (int i = 0; i < repres.length(); i++) {
                        char c = repres.charAt(i);
                        System.out.print(c);
                    }
                    break;
                case 3:
                    arvore.Menor_Valor();
                    break;
                case 4:
                    arvore.Maior_Valor();
                    break;
                case 5:
                    arvore.mostrarFolhas();
                    break;
                case 6:
                    System.out.print("Digite o valor para encontrar os ancestrais: ");
                    int valorAncestral = scanner.nextInt();
                    System.out.println("Ancestrais do nó " + valorAncestral + ":");
                    arvore.mostrarAncestrais(arvore.getRaiz(), valorAncestral);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (escolha != 0);

        scanner.close();



    }
}

class No_Arvore {
    int no;
    No_Arvore no_esquerda;
    No_Arvore no_direita;

    public No_Arvore(int no) {
        this.no = no;
        no_esquerda = null;
        no_direita = null;
    }

    


}  

/**
 * Classe de inicialização da árvore binária
 */
class Arvore_Binaria{
    private No_Arvore raiz;

    public Arvore_Binaria(){
        raiz = null;
    }


    /**
     * Metodo de inserção de um valor dentro da árvore
     * @param valor
     */ 
    public No_Arvore getRaiz() {
        return raiz;
    }
    public void inserir_no(int valor){
        if (raiz == null) {
            this.raiz = new No_Arvore(valor);
            return;
        }

        No_Arvore current = raiz;
        No_Arvore parent = null;


        while (current != null) {
            parent = current;   
            if (valor < current.no) {
                current = current.no_esquerda;
            } else if (valor > current.no) {
                current = current.no_direita;
            } else {
                return; 
            }
        }


        if (valor < parent.no) {
            parent.no_esquerda = new No_Arvore(valor);
        } else {
            parent.no_direita = new No_Arvore(valor);
        }
    }



    /**
     * Visualição dos valores da árvore usando a sequencia Em Ordem
     */

    public void Em_Ordem_Vizualizacao(){
        if (raiz == null){
            return;
        }

        Stack stack = new Stack<>();
        No_Arvore current = raiz;

        while (current != null || !stack.isEmpty()  ) {
            while (current != null) {
                stack.push(current);
                current = current.no_esquerda;
            }

            current = (No_Arvore) stack.pop();
            System.out.println(current.no + " ");
            current = current.no_direita;
        }
    }
    
    
    /**
     * Metodo que pega o menor valor da arvore
     */
    public void Menor_Valor(){
        No_Arvore no_atual = raiz;

        while (no_atual.no_esquerda != null) {
            no_atual = no_atual.no_esquerda;
        }

        System.out.println("Menor valor da árvore: " + no_atual.no);
    }

    /**
     * Metodo que pega o maior valor da arvore
     */
    public void Maior_Valor(){
        No_Arvore no_atual = raiz;

        while (no_atual.no_direita != null) {
            no_atual = no_atual.no_direita;
        }

        System.out.println("Maior valor da árvore: " + no_atual.no);
    }


    /**
     * Metodo para mostrar a representação da arvore
     * @return
     */
    public String Mostrar_Arvore() {
        if (raiz == null) {
            return "";
        }
    
        StringBuilder vizu = new StringBuilder();
    
        Mostrar_ArvoreRecursivo(raiz, vizu);
    
        return vizu.toString();
    }
    
    /**
     * Metodo que precorre a arvore recursivamente para verificar os nós filhos e adicionar ao StringBuilder
     * @param no
     * @param vizu
     */
    private void Mostrar_ArvoreRecursivo(No_Arvore no, StringBuilder vizu) {
        if (no == null) {
            return;
        }
    
        vizu.append(no.no);
    
        if (no.no_esquerda != null || no.no_direita != null) {
            vizu.append("(");
            Mostrar_ArvoreRecursivo(no.no_esquerda, vizu);
    
            if (no.no_direita != null) {
                vizu.append(" ");
                Mostrar_ArvoreRecursivo(no.no_direita, vizu);
            }
            vizu.append(")");
        }
    }




    /**
     * Função para pegar as folhas da arvore
     */
    public void mostrarFolhas() {
        System.out.print("Folhas da árvore: ");
        mostrarFolhasRecursivo(raiz);
        System.out.println();
    }
    /**
     * Imprime apenas os nós que não possuem filhos
     * @param no
     */
    private void mostrarFolhasRecursivo(No_Arvore no) {
        if (no == null) {
            return;
        }
    
        if (no.no_esquerda == null && no.no_direita == null) {
            System.out.print(no.no + " ");
        }
    
        mostrarFolhasRecursivo(no.no_esquerda);
        mostrarFolhasRecursivo(no.no_direita);
    }


    /**
     * @param raiz
     * @param data
     */
    void mostrarAncestrais(No_Arvore root, int data) {
        if (root.no == data){
            return;
        }
        System.out.println(root.no);

        if(data < root.no) {
            mostrarAncestrais(root.no_esquerda, data);
        }
        if (data>root.no) {
            mostrarAncestrais(root.no_direita, data);
        }
    }

}


