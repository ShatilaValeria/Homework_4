package DB.operationRequest;

public interface OperationRequest {

    void requestForReplenishment(int accountId, String name, double money);

    void withdrawalOfFounds(int accountId, String name, double money);

    void checkingTheBalance(int accountId, String currency);
}
