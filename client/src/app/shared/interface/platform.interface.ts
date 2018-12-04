import {Company} from "./company.interface";

export interface PlatformInterface {
  id: number;
  name: string;
  releaseYear: number;
  company: Company
}
