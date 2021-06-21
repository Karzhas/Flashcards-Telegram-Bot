package kz.karzhas.domain.usecases;

public interface BaseUseCaseWithParam<RequestParam, Response> {
    Response execute(RequestParam requestParam);
}
