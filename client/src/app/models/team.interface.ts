import {Player} from './player.interface'

export interface Team {
  id: number,
  name: string,
  teamName: string,
  isACpu: boolean,
  QB: Player,
  WR1: Player,
  WR2: Player,
  RB1: Player,
  RB2: Player,
  TE: Player,
  FLEX: Player,
  DST: Player,
  bench: Player[]
}
