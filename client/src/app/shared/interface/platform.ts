import {Company} from "./company";

export interface Platform {
  id: number;
  name: string;
  releaseYear: number;
  company: Company
}
