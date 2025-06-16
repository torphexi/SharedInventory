import { ApplicationConfig, importProvidersFrom } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { ApiModule } from '../generated/api.module';
import { Configuration, ConfigurationParameters } from '../generated/configuration';
import { provideHttpClient } from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [provideRouter(routes),provideHttpClient(), importProvidersFrom([ApiModule.forRoot(apiConfigFactory)])]
};

export function apiConfigFactory(): Configuration {
  const params: ConfigurationParameters = {
    basePath: "http://localhost", // Updated to include /api for all endpoints
  };
  return new Configuration(params);
}