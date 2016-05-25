
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

object HalfR extends Operation {
  val opCode = "hlf r"
  def execute(vm: VirtualMachine, register: Option[Register], offset: Option[Offset]): VirtualMachine = {
    val currentRegister = register.get
    val currentRegisterValue = vm.memory.contents(currentRegister).value
    val newInstructionIndex = vm.currentInstructionIndex + 1
    val newContents = vm.memory.contents.updated(
      currentRegister, Value(currentRegisterValue/2))

    vm.copy(currentInstructionIndex = newInstructionIndex, memory = vm.memory.copy(
      contents = newContents))
  }
}

object TripleR extends Operation {
  val opCode = "tpl r"
  def execute(vm: VirtualMachine, register: Option[Register], offset: Option[Offset]): VirtualMachine = {
    val currentRegister = register.get
    val currentRegisterValue = vm.memory.contents(currentRegister).value
    val newInstructionIndex = vm.currentInstructionIndex + 1
    val newContents = vm.memory.contents.updated(
      currentRegister, Value(currentRegisterValue * 3))

    vm.copy(currentInstructionIndex = newInstructionIndex, memory = vm.memory.copy(
      contents = newContents))
  }
}

object JumpIfEven extends Operation {
  val opCode = "jie r"
  def execute(vm: VirtualMachine, register: Option[Register], offset: Option[Offset]): VirtualMachine = {
    val currentRegister = register.get
    val currentRegisterValue = vm.memory.contents(currentRegister).value

    val newInstructionIndex = if (currentRegisterValue % 2 == 0) {
      vm.currentInstructionIndex + offset.get.value
    } else {
      vm.currentInstructionIndex + 1
    }

    vm.copy(currentInstructionIndex = newInstructionIndex)
  }
}

object JumpIfOne extends Operation {
  val opCode = "jio r"
  def execute(vm: VirtualMachine, register: Option[Register], offset: Option[Offset]): VirtualMachine = {
    val currentRegister = register.get
    val currentRegisterValue = vm.memory.contents(currentRegister).value

    val newInstructionIndex = if (currentRegisterValue == 1) {
      vm.currentInstructionIndex + offset.get.value
    } else {
      vm.currentInstructionIndex + 1
    }

    vm.copy(currentInstructionIndex = newInstructionIndex)
  }
}
