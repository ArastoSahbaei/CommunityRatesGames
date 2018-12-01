import {Company} from "./company";
import {Platform} from "./platform";

export interface Game {
  id: number;
  title: string;
  company: Company;
  platforms: Platform[];
  averageRating: number;
}
