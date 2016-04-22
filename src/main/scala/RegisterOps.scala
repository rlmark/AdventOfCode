
trait Operation {
  val opCode: String
  def op: (VirtualMachine, Instruction) => VirtualMachine
}

class IncrementR extends Operation {
  val opCode = "inc"
  def op(vm: VirtualMachine, instruction: Instruction): VirtualMachine = {
    val currentRegister: Register = instruction.register.get
    val newContents: Map[Register, Value] = vm.memory.contents.updated(
      currentRegister, vm.memory.contents + 1 )
    vm.copy(memory = vm.memory.copy(
      contents = newContents))
  }
}

class JumpOffset extends Operation {
  val opCode = "jmp"
  def op = ???
}
//class HalfR(currentVal: Int) extends Operation {
//  val
//  def op(currentVal: Int): Int = {
//    currentVal / 2
//  }
//}
//
//case class TripleR(currentVal: Int) extends Operation {
//  def op(currentVal: Int): Int = {
//    currentVal * 3
//  }
//}



// jmp offset is a jump; it continues with the instruction offset away relative to itself.
// jie r, offset is like jmp, but only jumps if register r is even ("jump if even").
// jio r, offset is like jmp, but only jumps if register r is 1 ("jump if one", not odd).

/*
These last 3 are harder. Maybe in addition to current value, I would also need something like "current index" in a list.
or build up a chain of funcitons
 */

