public class LoanDatabase {
    private ListNodeLoan head;

    // Método para insertar un préstamo
    public void insertLoan(Loan loan) {
        ListNodeLoan newNode = new ListNodeLoan(loan);
        if (head == null) {
            head = newNode;
        } else {
            ListNodeLoan current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    // Método para devolver un préstamo dado su ID
    public boolean returnLoan(String loanId) {
        if (head == null) {
            return false; // No hay préstamos en la base de datos
        }

        ListNodeLoan current = head;
        ListNodeLoan prev = null;

        // Buscar el préstamo con el ID dado
        while (current != null && !current.getLoan().getId().equals(loanId)) {
            prev = current;
            current = current.getNext();
        }

        // Si se encontró el préstamo
        if (current != null) {
            // Eliminar el nodo del préstamo
            if (prev == null) {
                // El préstamo es el primer nodo
                head = head.getNext();
            } else {
                prev.setNext(current.getNext());
            }
            return true; // Devolución exitosa
        } else {
            return false; // No se encontró el préstamo con ese ID
        }
    }

    // Método para obtener todos los préstamos
    public ListNodeLoan getAllLoans() {
        return head; // Retorna la lista de todos los préstamos
    }
}
