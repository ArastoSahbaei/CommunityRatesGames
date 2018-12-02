import {Company} from "./company";
import {Platform} from "./platform";

export interface GameInterface {
  id: number;
  title: string;
  company: Company;
  platforms: Platform[];
  averageRating: number;
}
