package de.mediqon.generic.data_dashboard.authenticatin;

//@Replaces(AccessRefreshTokenLoginHandler.class)
public class AppLoginHandler /*extends AccessRefreshTokenLoginHandler*/ {
    /*public AppLoginHandler(AccessRefreshTokenGenerator accessRefreshTokenGenerator) {
        super(accessRefreshTokenGenerator);
    }

    @Override
    public MutableHttpResponse<?> loginSuccess(UserDetails userDetails, HttpRequest<?> request) {
        Optional<AccessRefreshToken> accessRefreshTokenOptional = this.accessRefreshTokenGenerator.generate(userDetails);

        if(accessRefreshTokenOptional.isPresent()){
            AccessRefreshToken refreshToken = accessRefreshTokenOptional.get();
            Map<String, Object> map = userDetails.getAttributes("r", "a");

            RefreshTokenUserModel tokenUserModel = (RefreshTokenUserModel)map.get("ue");
            tokenUserModel.setRefreshToken(refreshToken.getRefreshToken());
            tokenUserModel.setAccessToken(refreshToken.getAccessToken());
            tokenUserModel.setExpiresIn(refreshToken.getExpiresIn());

            return HttpResponse.ok(tokenUserModel);

        }

        return HttpResponse.serverError();
    }

    @Override
    public MutableHttpResponse<?> loginRefresh(UserDetails userDetails, String refreshToken, HttpRequest<?> request) {
        return super.loginRefresh(userDetails, refreshToken, request);
    }

    @Override
    public MutableHttpResponse<?> loginFailed(AuthenticationResponse authenticationFailed, HttpRequest<?> request) {
        return super.loginFailed(authenticationFailed, request);
    }*/
}
