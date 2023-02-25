import {KeycloakService} from "keycloak-angular";

export function initializeKeycloak(keycloak: KeycloakService) {
  return () => {
    keycloak.init({
      config: {
        url: 'http://localhost:8443',
        realm: 'zodiac-vk-realm',
        clientId: 'zodiac-vk-frontend-client'
      },
      initOptions: {
        onLoad: 'login-required',
        flow: 'standard'
      }
    });
  };
}

