import {Company} from "./company.interface";
import {PlatformInterface} from "./platform.interface";

export interface Game {
  id: number;
  title: string;
  company: Company;
  platforms: PlatformInterface[];
  averageRating: number;
}
