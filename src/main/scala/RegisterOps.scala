
trait Operation {
  val opCode: String
  def execute(vm: VirtualMachine, register: Option[Register], offset: Option[Offset]): VirtualMachine

  def add(x:Int) = (y:Int) => x + y
  def multiply(x: Int) = (y: Int) => x * y
  def divide(x: Int) = (y: Int) => y/x

  def incrementAppropriately(vm: VirtualMachine,
                             register: Register,
                             increment: Int => Int) = {
    val currentRegisterValue = vm.memory.contents(register).value
    val newInstructionIndex = vm.currentInstructionIndex + 1
    val newContents: Map[Register, Value] = vm.memory.contents.updated(
      register, Value(increment(currentRegisterValue)))

    vm.copy(currentInstructionIndex = newInstructionIndex, memory = vm.memory.copy(
      contents = newContents))
  }

  def getJumpBasedOnRegister(vm: VirtualMachine,
                             register: Register,
                             offset: Option[Offset],
                             f: Int => Boolean) = {
    val currentRegisterValue = vm.memory.contents(register).value

    val newInstructionIndex = if (f(currentRegisterValue)) {
      vm.currentInstructionIndex + offset.get.value
    } else {
      vm.currentInstructionIndex + 1
    }

    vm.copy(currentInstructionIndex = newInstructionIndex)
  }
}

object IncrementR extends Operation {
  val opCode = "inc"
  def execute(vm: VirtualMachine, register: Option[Register], offset: Option[Offset]): VirtualMachine =
    incrementAppropriately(vm, register.get, add(1))
}

object TripleR extends Operation {
  val opCode = "tpl r"
  def execute(vm: VirtualMachine, register: Option[Register], offset: Option[Offset]): VirtualMachine =
    incrementAppropriately(vm, register.get, multiply(3))
}

object HalfR extends Operation {
  val opCode = "hlf r"
  def execute(vm: VirtualMachine, register: Option[Register], offset: Option[Offset]): VirtualMachine = {
    incrementAppropriately(vm, register.get, divide(2))
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

object JumpIfEven extends Operation {
  val opCode = "jie r"
  def execute(vm: VirtualMachine, register: Option[Register], offset: Option[Offset]): VirtualMachine = {
    def isEven(registerValue: Int) = registerValue % 2 == 0
    getJumpBasedOnRegister(vm, register.get, offset, isEven)
  }
}

object JumpIfOne extends Operation {
  val opCode = "jio r"
  def execute(vm: VirtualMachine, register: Option[Register], offset: Option[Offset]): VirtualMachine = {
    def isOne(registerValue: Int) = registerValue == 1
    getJumpBasedOnRegister(vm, register.get, offset, isOne)
  }
}





// For purposes of talking through refactoring.
//    val currentRegister = register.get
//    val currentRegisterValue = vm.memory.contents(currentRegister).value
//
//    val newInstructionIndex = if (currentRegisterValue == 1) {
//      vm.currentInstructionIndex + offset.get.value
//    } else {
//      vm.currentInstructionIndex + 1
//    }
//
//    vm.copy(currentInstructionIndex = newInstructionIndex)
//  }

//    val currentRegister = register.get
//    val currentRegisterValue = vm.memory.contents(currentRegister).value
//    val newInstructionIndex = vm.currentInstructionIndex + 1
//    val newContents = vm.memory.contents.updated(
//      currentRegister, Value(currentRegisterValue/2))
//
//    vm.copy(currentInstructionIndex = newInstructionIndex, memory = vm.memory.copy(
//      contents = newContents))


//def incrementAppropriately(currentRegisterValue: Int, opCode: String): Value = opCode match {
//  case "inc" => Value(currentRegisterValue + 1) 
//    case "hlf r" => Value(currentRegisterValue/2) 
//    case "tpl r" => Value(currentRegisterValue * 3) 
//  }
//
//    def setNewVMForRegisterChange(vm: VirtualMachine, 
//                          currentRegister: Register, 
//                          newCurrentRegisterValue: Value, 
//                          currentInstructionIndex: Int) = { 
//    val newContents = vm.memory.contents.updated(currentRegister, newCurrentRegisterValue) 
//    vm.copy(currentInstructionIndex + 1, memory = vm.memory.copy(contents = newContents)) 
//  }
//
//    def getCurrentRegister(vm: VirtualMachine, register: Register) = { 
//    val currentRegister: Register = register 
//    val currentRegisterValue = vm.memory.contents(currentRegister).value 
//    (currentRegister, currentRegisterValue) 
//  }