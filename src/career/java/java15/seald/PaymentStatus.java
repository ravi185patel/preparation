package career.java.java15.seald;

public sealed interface PaymentStatus
    permits Success, Failed, Pending {}
