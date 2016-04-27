
trait Operation {
  val opCode: String
  def execute(vm: VirtualMachine, register: Option[Register], offset: Option[Offset]): VirtualMachine
}

object IncrementR extends Operation {
  val opCode = "inc"
  def execute(vm: VirtualMachine, register: Option[Register], offset: Option[Offset]): VirtualMachine = {
    val currentRegister: Register = register.get
    val currentRegisterValue = vm.memory.contents(currentRegister).value
    val newInstructionIndex = vm.currentInstructionIndex + 1
    val newContents: Map[Register, Value] = vm.memory.contents.updated(
      currentRegister, Value(currentRegisterValue + 1))
    vm.copy(currentInstructionIndex = newInstructionIndex, memory = vm.memory.copy(
      contents = newContents))
  }
}

object JumpOffset extends Operation {
  val opCode = "jmp"
  def execute(vm: VirtualMachine, register: Option[Register], offset: Option[Offset]) = {
    val offsetValue = offset.get.value
    val newInstructionIndex = vm.currentInstructionIndex + offsetValue 
    vm.copy(currentInstructionIndex = newInstructionIndex)
  }
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

