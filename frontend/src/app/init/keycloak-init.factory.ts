import {KeycloakService} from "keycloak-angular";
import {firstValueFrom, from, switchMap} from "rxjs";
import {ConfigInitService} from "./config-init.service";

export function initializeKeycloak(
  keycloak: KeycloakService,
  configService: ConfigInitService
) {
  return () =>
    firstValueFrom(
      configService.getConfig()
        .pipe(
          switchMap<any, any>((config) => {
            return from(keycloak.init({
              config: {
                url: config['KEYCLOAK_URL'],
                realm: config['KEYCLOAK_REALM'],
                clientId: config['KEYCLOAK_CLIENT_ID'],
              },
              initOptions: {
                onLoad: 'login-required',
                checkLoginIframe: false
              }
            }))
          })
        ));
}

