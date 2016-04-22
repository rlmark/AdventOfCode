
case class Instruction(op: Operation, register: Option[Register], offset: Option[Offset])
/// / Note we don't need to pass in actual register, just the reference to the register.
case class Memory(contents: Map[Register, Value] )
case class Register(name: String)
case class Value(value: Int)

case class Offset(value: Int)

case class Program(instructions: Vector[Instruction])

case class VirtualMachine(currentInstruction: Int, memory: Memory, program: Program)