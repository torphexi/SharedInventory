export * from './greetingResource.service';
import { GreetingResourceService } from './greetingResource.service';
export * from './pathfinderDataResource.service';
import { PathfinderDataResourceService } from './pathfinderDataResource.service';
export const APIS = [GreetingResourceService, PathfinderDataResourceService];
